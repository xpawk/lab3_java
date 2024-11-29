package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShapeDescriber {

    private static final Logger logger = LoggerFactory.getLogger(ShapeDescriber.class);

    public void describe(Shape shape) {
        logger.info("Opis figury: Typ={} Kolor={}", shape.getClass().getSimpleName(), shape.getColorDescription());
        logger.info("Pole figury: {}", shape.getArea());
        logger.info("Obwód figury: {}", shape.getPerimeter());

        System.out.println("Pole: " + shape.getArea());
        System.out.println("Obwód: " + shape.getPerimeter());
        System.out.println("Kolor: " + shape.getColorDescription());
    }
}