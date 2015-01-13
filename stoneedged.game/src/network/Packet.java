package network;

public class Packet {

    protected PacketType type;

    public Packet(PacketType type) {
        this.type = type;
    }

    public PacketType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }

    // Enum
    public static enum PacketType {
        DEFAULT(-1), LOG_IN(0), LOG_OUT(1), MOVE(2), MESSAGE(3);

        private int type;

        PacketType(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            switch (this) {
                case LOG_IN:
                    return "00";
                case LOG_OUT:
                    return "01";
                case MOVE:
                    return "02";
                case MESSAGE:
                    return "03";
                default:
                    return "-1";
            }
        }

        public static PacketType getType(String type) {
            if (type.equals(LOG_IN.toString()))
                return LOG_IN;
            if (type.equals(LOG_OUT.toString()))
                return LOG_OUT;
            if (type.equals(MOVE.toString()))
                return MOVE;
            if (type.equals(MESSAGE.toString()))
                return MESSAGE;
            return DEFAULT;
        }
    }
}
