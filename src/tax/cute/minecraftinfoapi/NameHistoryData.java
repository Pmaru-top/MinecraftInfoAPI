package tax.cute.minecraftinfoapi;

public class NameHistoryData {
    private final String name;
    private final long changedToAt;

    public NameHistoryData(
            String name,
            long changedToAt
    ) {
        this.name = name;
        this.changedToAt = changedToAt;
    }

    public String getName() {
        return name;
    }

    public long getChangedToAt() {
        return changedToAt;
    }
}
