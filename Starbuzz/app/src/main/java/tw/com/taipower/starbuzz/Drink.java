package tw.com.taipower.starbuzz;

/**
 * Created by new on 2016/3/14.
 */
public class Drink {
    public static final Drink[] drinks = new Drink[]{
            new Drink("Latte", "A couple of espresso shots with steamed milk", R.drawable.latte),
            new Drink("Cappuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino),
            new Drink("Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter)
    };
    private String name;
    private String description;
    private int imageRescourceId;

    public Drink(String name, String description, int imageRescourceId) {
        this.name = name;
        this.description = description;
        this.imageRescourceId = imageRescourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageRescourceId() {
        return imageRescourceId;
    }

    public String toString() {
        //Log.d("Drink Name：", this.name);
        return this.name;
    }
}
