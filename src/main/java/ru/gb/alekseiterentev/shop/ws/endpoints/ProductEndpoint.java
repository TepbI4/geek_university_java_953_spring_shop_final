package ru.gb.alekseiterentev.shop.ws.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.alekseiterentev.shop.beans.services.ProductService;
import ru.gb.alekseiterentev.shop.model.Product;
import ru.gb.alekseiterentev.shop.ws.model.products.GetAllProductsRequest;
import ru.gb.alekseiterentev.shop.ws.model.products.GetAllProductsResponse;
import ru.gb.alekseiterentev.shop.ws.model.products.GetProductByIdRequest;
import ru.gb.alekseiterentev.shop.ws.model.products.GetProductsByIdResponse;
import ru.gb.alekseiterentev.shop.ws.model.products.ProductWs;

import java.util.function.Function;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {

    private static final String NAME_SPACE_URI = "http://www.alekseiterentev.ru/shop/ws/products";
    private final ProductService productService;
    public static final Function<Product, ProductWs> functionEntityToSoap = se -> {
        ProductWs s = new ProductWs();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    /*

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.alekseiterentev.ru/shop/ws/products">
        <soapenv:Header/>
        <soapenv:Body>
            <f:getAllProductsRequest/>
        </soapenv:Body>
    </soapenv:Envelope>

    */

    @PayloadRoot(namespace = NAME_SPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAll().stream().map(functionEntityToSoap).forEach(response.getProducts()::add);
        return response;
    }

    /*
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.alekseiterentev.ru/shop/ws/products">
        <soapenv:Header/>
        <soapenv:Body>
            <f:getProductByIdRequest>
                <f:id>1</f:id>
            </f:getProductByIdRequest>
        </soapenv:Body>
    </soapenv:Envelope>
    */

    @PayloadRoot(namespace = NAME_SPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductsByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductsByIdResponse response = new GetProductsByIdResponse();
        productService.findById(request.getId()).map(functionEntityToSoap).ifPresent(response::setProduct);
        return response;
    }
}
