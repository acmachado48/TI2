package app;

import static spark.Spark.*;

import model.Carro;
import service.CarroService;

public class Aplicacao {
    
    private static CarroService carroService = new CarroService();
    
    public static void main(String[] args) {
        port(6789);

        post("/carro", (request, response) -> {
            Carro carro = (Carro) carroService.add(request, response);
            String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Informações do Carro</title><style>table {width: 100%; border-collapse: collapse;} th, td {padding: 8px; text-align: left; border-bottom: 1px solid #ddd;} th {background-color: #f2f2f2;}</style></head><body><h2>Informações do Carro Adicionado</h2><table><tr><th>Cor</th><td>" + carro.getCor() + "</td></tr><tr><th>Placa</th><td>" + carro.getPlaca() + "</td></tr><tr><th>Ano</th><td>" + carro.getAno() + "</td></tr><tr><th>Marca</th><td>" + carro.getMarca() + "</td></tr></table></body></html>";
            return html;
        });


            get("/carro/:placa", (request, response) -> carroService.get(request, response));

        put("/carro/:placa", (request, response) -> carroService.update(request, response));

        delete("/carro/:placa", (request, response) -> carroService.remove(request, response));

        get("/carro", (request, response) -> carroService.getAll(request, response));
        
        get("/carro/delete/:placa", (request, response) -> carroService.remove(request, response));

    }
}