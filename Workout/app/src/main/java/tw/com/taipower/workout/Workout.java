package tw.com.taipower.workout;

/**
 * Created by new on 2016/3/20.
 */
public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = new Workout[]{
        new Workout("The Limb Lossener", "5 Handstand push-ups\n10 1-legged squart\n15 Pull-ups"),
        new Workout("Core Agnoy","100 Pull-ups\n100 Push-ups\n100 Sit-ups\n100 Squats"),
        new Workout("The Wimp Special","5 Pull-ups\n10 Push-ups\n15 Squats"),
        new Workout("Strength and Length","500 meter run\n21 x 1.5 pood kettlebell swing\n21 x pull-ups")
    };

    public Workout(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
