package com.example.springgateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MicroserviceApp {
    private String id;
    private String path;
    private String uri;
}
