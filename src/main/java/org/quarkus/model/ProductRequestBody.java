package org.quarkus.model;

import io.smallrye.common.constraint.NotNull;

public class ProductRequestBody {
    @NotNull
    public long id;
    @NotNull
    public String name, brand;
    @NotNull
    public double price;
}
