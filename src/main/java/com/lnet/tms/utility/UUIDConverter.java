package com.lnet.tms.utility;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.UUID;

public class UUIDConverter {

    public static UUID toUUID(String source) {

        if (StringUtils.isEmpty(source)) return null;
        source = source.trim();

        if (source.length() == 36) {
            return UUID.fromString(source);
        } else if (source.length() == 32) {
            return fromByteArray(DatatypeConverter.parseHexBinary(source));
        }

        return null;
    }

    public static String toRaw(UUID uuid) {
        return uuid.toString().replaceAll("-", "");
    }

    public static byte[] toByteArray(UUID uuid) {
        byte[] byteArray = new byte[(Long.SIZE / Byte.SIZE) * 2];
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.put(new long[]{uuid.getMostSignificantBits(), uuid.getLeastSignificantBits()});
        return byteArray;
    }

    public static UUID fromByteArray(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        LongBuffer longBuffer = buffer.asLongBuffer();
        return new UUID(longBuffer.get(0), longBuffer.get(1));
    }


    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

        String uuidStr = uuid.toString();

        String uuidRaw = toRaw(uuid);
        System.out.println(uuidRaw);

        System.out.println(toUUID(uuidStr));
        System.out.println(toUUID(uuidRaw));

        System.out.println(fromByteArray(toByteArray(uuid)));
    }

}
