package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.SneakyThrows;

public class Main {

    private static String data =
        "{\"messages\": [{\"id\": \"INTR-402e448a-0417-45b8-952e-83dcbf3e9997\", \"phone\": \"+79260750753\", \"event\": \"INTR_jur_reports\", \"source\": \"INTR\", \"channel\": \"EMAIL\", \"parameters\": {\"isHtml\": true, \"subject\": \"Брокерский отчет ВТБ за 07.06.2024\", \"periodTo\": \"2024-06-07\", \"bo_account\": \"1101HB\", \"periodFrom\": \"2024-06-07\", \"reportType\": \"JUR_BROKERAGE_REPORT\", \"senderName\": \"ПАО Банк ВТБ\", \"attachments\": \"[{\\\"uuid\\\":\\\"4d2cb20c-42ad-462f-921a-31ee59a07777\\\",\\\"inline\\\":false},{\\\"uuid\\\":\\\"4d2cb20c-42ad-462f-921a-31ee59a07777\\\",\\\"inline\\\":false}]\", \"senderEmail\": \"k3-msp-unp-email-svc@vtb.ru\", \"bo_agreement\": \"1101WS\", \"subAgreement\": \"1****B\", \"isAttachmentAllowed\": \"true\"}}], \"apiVersion\": \"v3\"}";

    private static final char DEFAULT_REPLACEMENT_CHAR = '*';

    @SneakyThrows
    public static void main(String[] args) {
        fun(4);
    }

    static void fun(int x) {
        if (x > 0) {
            fun(--x);
            System.out.println(x + "");
            fun(--x);
        }
    }

    private static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            return email;
        }
        return Arrays.stream(email.split(","))
            .map(em -> {
                var parts = em.split("@");
                if (parts.length != 2) {
                    return em;
                }
                var masked = (parts[0].length() > 2)
                    ? parts[0].substring(0, 2) + "****"
                    : parts[0];
                return masked + "@" + parts[1];
            })
            .collect(Collectors.joining(","));
    }
    //
    //    private static String maskEmail(String email) {
    //        if (email == null || !email.contains("@")) return email;
    //        var stringBuilder = new StringBuilder();
    //        var split = email.split(",");
    //
    //        Stream.of(split)
    //                .forEach(em -> {
    //                    String[] parts = em.split("@");
    //                    if (parts[0].length() > 2) {
    //                        stringBuilder.append(parts[0], 0, 2).append("****@").append(parts[1]).append(",");
    //                    } else {
    //                        stringBuilder.append(email).append(",");
    //                    }
    //                });
    //        return stringBuilder.toString();
    //    }

    //        ObjectMapper mapper = new ObjectMapper();
    //        var jsonNode = mapper.readTree(data);
    //        var email = jsonNode.findPath("email").asText();
    //        var phone = jsonNode.findPath("phone").asText();
    //        var acc = jsonNode.findPath("bo_account").asText();
    //
    //        ((ObjectNode) jsonNode.get("messages").get(0)).put("email", maskEmail(email));
    //        ((ObjectNode) jsonNode.get("messages").get(0)).put("phone", replaceAccordingSecurityRequirements(phone));
    //        ((ObjectNode) jsonNode.get("messages").get(0).get("parameters")).put("bo_account", replaceAccordingSecurityRequirements(acc));
    //
    //        System.out.println(jsonNode);
    //    }
    //
    //    private static String maskEmail(String email) {
    //        var pattern = Pattern.compile("(\\w+)@(\\w+\\.\\w+)");
    //        var matcher = pattern.matcher(email);
    //        var result = new StringBuilder();
    //
    //        int lastEnd = 0;
    //        while (matcher.find()) {
    //            // Добавляем непропущенную часть строки перед совпадением
    //            result.append(email, lastEnd, matcher.start()+1);
    //
    //            // Получаем имя пользователя (часть до '@')
    //            String username = matcher.group(1);
    //            // Меняем первые два символа имени пользователя на '*'
    //            String maskedUsername = username.replaceFirst("\\w+", "*".repeat(username.length()));
    //
    //            // Добавляем замаскированное имя пользователя и доменное имя
    //            result.append(maskedUsername).append('@').append(matcher.group(2));
    //
    //            // Обновляем lastEnd для следующей итерации
    //            lastEnd = matcher.end();
    //        }
    //
    //        // Добавляем оставшуюся часть строки после последнего совпадения
    //        if (lastEnd < email.length()) {
    //            result.append(email.substring(lastEnd));
    //        }
    //        return result.toString();
    //    }
    //
    //    public static String replaceAccordingSecurityRequirements(String value) {
    //        return switch (value.length()) {
    //            case 0 -> value;
    //            case 1 -> String.valueOf(DEFAULT_REPLACEMENT_CHAR);
    //            case 2 -> replaceInRange(value, 1, 1, true);
    //            case 3 -> replaceInRange(value, 1, 2, true);
    //            case 4 -> replaceInRange(value, 1, 3, true);
    //            case 5, 6, 7, 8, 9 -> replaceInRange(value, 1, 1 + (int) ceil(value.length() * 0.6), false);
    //            case 10, 11, 12, 13, 15 -> replaceInRange(value, 2, 2 + (int) ceil(value.length() * 0.6), false);
    //            default -> replaceLongString(value);
    //        };
    //    }
    //
    //    private static String replaceLongString(String value) {
    //        var middleIndex = (int) floor(value.length() / 2.0);
    //        var maskedAmount = (int) ceil(value.length() * 0.6);
    //        var v = maskedAmount / 2.0;
    //        var maskedToTheLeft = (int) (value.length() % 2 == 0 ? ceil(v) : floor(v));
    //        var maskedToTheRight = maskedAmount - maskedToTheLeft;
    //        return replaceInRange(value, middleIndex - maskedToTheLeft, middleIndex + maskedToTheRight, false);
    //    }
    //
    //    private static String replaceInRange(String value, int start, int end, boolean isClosed) {
    //        var n = isClosed
    //                ? end - start + 1
    //                : end - start;
    //        var prefix = value.substring(0, start);
    //        var sequence = String.valueOf(DEFAULT_REPLACEMENT_CHAR).repeat(n);
    //        var suffix = value.substring(start + n);
    //        return prefix + sequence + suffix;
    //    }

    //        ObjectMapper mapper = new ObjectMapper();
    //        JsonNode jsonNode = mapper.readTree("{" +
    //                "\"efrmmodork@test.vtb.ru\":{" +
    //                "\"standIn\": \"false\"," +
    //                "\"mdmId\": \"15410621717\"" +
    //                "}" +
    //                "}");
    //        System.out.println(jsonNode);

    //        a.add(new Cat("b", false));
    //        a.add(new Cat("d", false));
    //        a.add(new Cat("c", true));
    //        a.add(new Cat(null, true));
    //        a.add(new Cat("e", null));
    //
    //        a.stream()
    //                .sorted(Comparator.comparing(Cat::getAge, Comparator.nullsLast(BooleanComparator.TRUE_LOW) )
    //                        .thenComparing(Cat::getName, Comparator.nullsLast(Comparator.naturalOrder())))
    //                .forEach(System.out::println);

}





