package com.example.springBootaws.dto;

import com.example.springBootaws.TestConfig;
import com.example.springBootaws.web.practice.HelloResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
@Import(TestConfig.class)
public class HelloResponseDtoTest {

    @Test
    void  helloResponseDtoTest() {
        String name = "honggilDong";
        int amount = 1000;

        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        assertThat(helloResponseDto.getName()).isEqualTo("honggilDong");
        assertThat(helloResponseDto.getAmount()).isEqualTo(1000);
    }
}
