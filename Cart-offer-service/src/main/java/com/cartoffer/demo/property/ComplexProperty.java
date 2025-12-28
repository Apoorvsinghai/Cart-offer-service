package com.cartoffer.demo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "complex")
public class ComplexProperty extends BaseProperty implements Serializable {
	private static final long serialVersionUID = 1L;
}
