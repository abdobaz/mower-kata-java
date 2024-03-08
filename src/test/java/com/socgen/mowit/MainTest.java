package com.socgen.mowit;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MainTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @Test
    void should_throw_file_not_found_exception() {
        assertThatThrownBy(()->Main.main(new String[]{"file"}))
                .isExactlyInstanceOf(FileNotFoundException.class);
    }

    @Test
    void acceptance_test() throws FileNotFoundException {

        //GIVEN
        System.setOut(new PrintStream(outputStreamCaptor));
        String[] args = new String[] {"src/test/resources/mowerInput.txt"};

        //WHEN
        Main.main(args);

        //THEN
        assertThat(outputStreamCaptor.toString().trim())
                .isEqualTo("1 3 N\r\n5 1 E");
    }

}