package com.example.demo;

import com.example.demo.domain.AuthorizationInfo;
import com.example.demo.domain.enumeration.Authorizer;
import com.example.demo.domain.enumeration.Status;
import com.example.demo.repository.AuthorizationInfoRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;

@Component
@Scope("singleton")
public class NFeScraper {


//    private static final Logger logger = LoggerFactory.getLogger(NFeScraper.class);

    @Autowired
    private AuthorizationInfoRepository authorizationInfoRepository;

    @Value("${nfe.url}")
    private String nfeUrl;

    @Scheduled(fixedRate = 300000) // 5 minutos em milissegundos
    public void scrapAndStore() {
        try {
            Document doc = Jsoup.connect(nfeUrl).get();
            Elements estados = doc.select("table.tabelaListagemDados tbody tr");

            // Verificar se há pelo menos uma linha de estado
            if (estados.isEmpty()) {
//                logger.warn("Nenhuma linha de estado encontrada.");
                return;
            }

            // Obter o cabeçalho da tabela
            Element cabecalho = estados.first();
            Elements colunasCabecalho = cabecalho.select("th");

            // Iterar sobre as linhas de estado, começando da segunda linha (índice 1)
            for (int i = 1; i < estados.size(); i++) {
                Element estado = estados.get(i);
                Elements cells = estado.select("td");

                // Verificar se o número de células é igual ao número de colunas do cabeçalho
                if (cells.size() != colunasCabecalho.size()) {
//                    logger.warn("Número de células na linha não corresponde ao cabeçalho da tabela.");
                    continue; // Ignorar esta linha e continuar com a próxima
                }

                // Processar as células e extrair as informações
                AuthorizationInfo authorizationInfo = parseAuthorizationInfo(cells);
                authorizationInfo.setRetrievalTime(Instant.now());

                // TODO melhor um save all
                authorizationInfoRepository.save(authorizationInfo);
            }
        } catch (IOException e) {
//            logger.error("Erro ao buscar dados do site da NF-e.", e);
        }
    }

    private AuthorizationInfo parseAuthorizationInfo(Elements cells) {
        AuthorizationInfo authorizationInfo = new AuthorizationInfo();
        authorizationInfo.setAuthorizer(Authorizer.fromString(cells.get(0).text()));
        authorizationInfo.setAuthorization4(getStatusFromImage(cells.get(1).select("img").attr("src")));
        authorizationInfo.setAuthorizationReturn4(getStatusFromImage(cells.get(2).select("img").attr("src")));
        authorizationInfo.setCancellation4(getStatusFromImage(cells.get(3).select("img").attr("src")));
        authorizationInfo.setProtocolQuery4(getStatusFromImage(cells.get(4).select("img").attr("src")));
        authorizationInfo.setServiceStatus4(getStatusFromImage(cells.get(5).select("img").attr("src")));
        authorizationInfo.setAverageTime(cells.get(6).text());
        authorizationInfo.setRegistryQuery4(getStatusFromImage(cells.get(7).select("img").attr("src")));
        authorizationInfo.setEventReception4(getStatusFromImage(cells.get(8).select("img").attr("src")));
        return authorizationInfo;
    }

    private Status getStatusFromImage(String imageCell) {
        if (imageCell.contains("bola_verde_P.png")) {
            return Status.AVAILABLE;
        } else if (imageCell.contains("bola_amarela_P.png")) {
            return Status.UNSTABLE;
        } else if (imageCell.contains("bola_vermelha_P.png")) {
            return Status.UNAVAILABLE;
        } else {
            return Status.UNKNOWN;
        }
    }
}
