package ru.job4j.urlshortcut.config;

import org.junit.jupiter.api.Test;
import ru.job4j.urlshortcut.config.CodeGenerate;

import static org.assertj.core.api.Assertions.*;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * CodeGenerateTest CodeGenerate тестирование генерации кода.
 * @author Dmitry Stepanov, user Dmitry
 * @since 29.07.2022
 */
class CodeGenerateTest {

    @Test
    public void whenGenerateCodeLengthZero() {
        CodeGenerate codeGenerate = new CodeGenerate.CodeGenerateBuilder()
                .build();
        String code = codeGenerate.generate(0);
        assertThat(code).isEqualTo("");
    }

    @Test
    public void whenGenerateLowerCodeLengthFive() {
        CodeGenerate codeGenerate = new CodeGenerate.CodeGenerateBuilder()
                .useLower(true)
                .build();
        String code = codeGenerate.generate(5);
        assertThat(code.length()).isEqualTo(5);
    }

    @Test
    public void whenGenerateUpperCodeLengthSex() {
        CodeGenerate codeGenerate = new CodeGenerate
                .CodeGenerateBuilder()
                .useUpper(true)
                .build();
        String code = codeGenerate.generate(6);
        assertThat(code.length()).isEqualTo(6);
    }

    @Test
    public void whenGenerateDigitCodeLengthEight() {
        CodeGenerate codeGenerate = new CodeGenerate
                .CodeGenerateBuilder()
                .useDigits(true)
                .build();
        String code = codeGenerate.generate(8);
        assertThat(code.length()).isEqualTo(8);
    }

    @Test
    public void whenGenerateAllCaseCodeLengthEight() {
        CodeGenerate codeGenerate = new CodeGenerate
                .CodeGenerateBuilder()
                .useLower(true)
                .useUpper(true)
                .useDigits(true)
                .build();
        String code = codeGenerate.generate(8);
        assertThat(code.length()).isEqualTo(8);
    }
}