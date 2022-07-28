package ru.job4j.urlshortcut.service;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.TextRandomProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.9.2.Контрольные вопросы
 * 2. Сервис - UrlShortCut [#13799]
 * CodeGenerate класс генерирует случайную последовательность символов случайной длины.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 28.07.2022
 */
public class CodeGenerate {
    private static final Logger LOG = LoggerFactory.getLogger(CodeGenerate.class.getSimpleName());

    /**
     * Цифры 48-57 unicode
     * Знаки препинания и символы 58-64, 91-96
     * Латиница верхний 65-90 unicode
     * Латиница нижний 97-122 unicode
     *
     * @param length
     * @return
     */
    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerate = new RandomStringGenerator
                .Builder()
                .withinRange(48, 57)
                .build();
        return pwdGenerate.generate(length);
    }

    public String generateRandomCharUpper(int length) {
        RandomStringGenerator pwdGenerate = new RandomStringGenerator
                .Builder()
                .withinRange(65, 90)
                .build();
        return pwdGenerate.generate(length);
    }

    public String generateRandomCharLower(int length) {
        RandomStringGenerator pwdGenerate = new RandomStringGenerator
                .Builder()
                .withinRange(97, 122)
                .build();
        return pwdGenerate.generate(length);
    }

    public static void main(String[] args) {
        CodeGenerate codeGenerate = new CodeGenerate();
        LOG.info(codeGenerate.generateRandomNumbers(5));
        LOG.info(codeGenerate.generateRandomCharLower(5));
        LOG.info(codeGenerate.generateRandomCharUpper(5));
    }
}
