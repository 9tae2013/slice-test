package com.domain.slicetest;


import com.domain.slicetest.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class SliceJsonTest {
    @Autowired
    private BasicJsonTester basicJson;

    @Autowired
    private JacksonTester<Product> json;

    @Test
    public void basicJson() throws Exception {
        assertThat(this.basicJson.from("{\"a\":\"b\"}")).hasJsonPathStringValue("@.a");
    }

    @Test
    public void testSerialize() throws Exception {
        Product product = new Product("product1", 20, 33);
        // Use JSON path based assertions
        assertThat(this.json.write(product))
                .hasJsonPathStringValue("@.name");
        assertThat(this.json.write(product))
                .extractingJsonPathStringValue("@.name")
                .isEqualTo("product1");
    }
}
