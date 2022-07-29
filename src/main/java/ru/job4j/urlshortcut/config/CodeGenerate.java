package ru.job4j.urlshortcut.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
public final class CodeGenerate {
    private static final Logger LOG = LoggerFactory.getLogger(CodeGenerate.class.getSimpleName());
    private static final String LOWER = "abcdfghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private final boolean useLower;
    private final boolean useUpper;
    private final boolean useDigits;

    private CodeGenerate() {
        throw new UnsupportedOperationException("Empty constructor is not supported.");
    }

    private CodeGenerate(CodeGenerateBuilder builder) {
        this.useLower = builder.useLower;
        this.useUpper = builder.useUpper;
        this.useDigits = builder.useDigits;
    }

    public static class CodeGenerateBuilder {
        private boolean useLower;
        private boolean useUpper;
        private boolean useDigits;

        public CodeGenerateBuilder() {
            this.useLower = false;
            this.useUpper = false;
            this.useDigits = false;
        }

        /**
         * Установить true если хотим включить
         * в код латинские буквы с нижним регистром.
         * По умолчанию false.
         *
         * @param useLower Используйте значение true,
         *                 для включения в набор символы нижнего регистра(adc-xyz)
         * @return this
         */
        public CodeGenerateBuilder useLower(boolean useLower) {
            this.useLower = useLower;
            return this;
        }

        /**
         * Установить true если хотим включить
         * в код латинские буквы с верхним регистром.
         * По умолчанию false.
         *
         * @param useUpper Используйте значение true,
         *                 для включения в набор символы верхнего регистра(ABC-XYZ)
         * @return this
         */
        public CodeGenerateBuilder useUpper(boolean useUpper) {
            this.useUpper = useUpper;
            return this;
        }

        /**
         * Установить true если хотим включить
         * в код цифр.
         * По умолчанию false.
         *
         * @param useDigits Используйте значение true,
         *                  для включения в набор цифр(012-789)
         * @return this
         */
        public CodeGenerateBuilder useDigits(boolean useDigits) {
            this.useDigits = useDigits;
            return this;
        }

        public CodeGenerate build() {
            return new CodeGenerate(this);
        }
    }

    /**
     * Метод генерирует код в зависимости от свойств use**, и заданной длины coda.
     *
     * @param length длина кода.
     * @return String сгенерированный код заданной длины.
     */
    public String generate(int length) {
        if (length <= 0) {
            return "";
        }
        StringBuffer code = new StringBuffer(length);
        SecureRandom random = new SecureRandom();
        List<String> codeCategories = new CopyOnWriteArrayList<>();
        if (useLower) {
            codeCategories.add(LOWER);
        }
        if (useUpper) {
            codeCategories.add(UPPER);
        }
        if (useDigits) {
            codeCategories.add(DIGITS);
        }
        for (int i = 0; i < length; i++) {
            String codeCategory = codeCategories.get(random.nextInt(codeCategories.size()));
            int position = random.nextInt(codeCategory.length());
            code.append(codeCategory.charAt(position));
        }
        LOG.info("Генерация кода length={}, code={}.", length, code);
        return new String(code);
    }
}
