//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.10.07 at 01:56:49 PM GMT+04:00 
//


package ru.gb.alekseiterentev.shop.ws.model.products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="productWs" type="{http://www.alekseiterentev.ru/shop/ws/products}productWs"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "productWs"
})
@XmlRootElement(name = "getProductsByIdResponse")
public class GetProductsByIdResponse {

    @XmlElement(required = true)
    protected ProductWs productWs;

    /**
     * Gets the value of the productWs property.
     * 
     * @return
     *     possible object is
     *     {@link ProductWs }
     *     
     */
    public ProductWs getProduct() {
        return productWs;
    }

    /**
     * Sets the value of the productWs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductWs }
     *     
     */
    public void setProduct(ProductWs value) {
        this.productWs = value;
    }

}
