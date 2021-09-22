package etphoneshome.objects;

/**
 * Enum storing the constant values for types of phone pieces that can be collected
 */
public enum PhonePieceType {

    ANTENNA(0),
    CHASSIS(1),
    KEYPAD(2);

    /**
     * Index of phone piece type used to grt image associate with that given phone pieced type
     */
    private final int index;

    PhonePieceType(int index) {
        this.index = index;
    }

    /**
     * Index of phone piece type used to grt image associate with that given phone pieced type
     * @return index
     */
    public int getIndex() {
        return index;
    }

}
