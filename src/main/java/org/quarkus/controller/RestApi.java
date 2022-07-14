package org.quarkus.controller;

import io.smallrye.common.constraint.NotNull;
import org.quarkus.model.Product;
import org.quarkus.model.ProductRequestBody;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/products")
public class RestApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProduct(){
        List<Product> products= Product.listAll();
        return Response.ok(products).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createProduct(Product product){
        product.persist(product);
        if (product.isPersistent()){
            return Response.created(URI.create("/products"+product.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createProduct2(@NotNull ProductRequestBody productRq){
//        Product product = new Product(productRq.id,productRq.name,productRq.brand,productRq.price);
//        product.persist();
//
//        return Response.ok(product).build();
//    }

    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct (@PathParam("id") Long id){
        boolean deleted = Product.deleteById(id);
        if(deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Long id,Product productrq){
        Product product = Product.findById(id);
        if(product == null){
            throw new WebApplicationException("Not found",404);
        }
        product.name = productrq.name;
        product.brand = productrq.brand;
        product.price = productrq.price;
        product.persist();

        return Response.ok(product).build();
    }

}
