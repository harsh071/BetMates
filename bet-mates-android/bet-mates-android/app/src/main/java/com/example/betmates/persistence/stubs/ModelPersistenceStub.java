package com.example.betmates.persistence.stubs;

import com.example.betmates.R;
import com.example.betmates.objects.content.Bet;
import com.example.betmates.objects.content.Model;
import com.example.betmates.objects.content.User;
import com.example.betmates.persistence.ModelPersistence;

import java.util.ArrayList;

public class ModelPersistenceStub implements ModelPersistence {
    int id;
    private ArrayList<Model> models;
    public ModelPersistenceStub() {
        id = 1;
        models = new ArrayList<>();
        Bet bet2 = new Bet(1000, "Will Jets Win the game tonight? ", "GameWinner", new User("Ayush", 19, 2000));
        Bet bet3 = new Bet(1000, "Can you use the terminal better than me?", "Terminal Wizzard", new User("Justin", 19, 2000));
        Bet bet4 = new Bet(1000, "Make 100 kills in fortnite by tomorrow.", "Fortnite", new User("Ray", 19, 2000));
        Bet bet5 = new Bet(1000, "Can you solve this equation? ", "Solve the Equation", new User("Eddy", 19, 2000));
        Bet bet6 = new Bet(1000, "Can you beat me in a game in FIFA? ", "FIFA", new User("Kaitlyn", 19, 2000));
        Bet bet7 = new Bet(1000, "Can you run 100m in 14 seconds? ", "Sprinting", new User("Rob", 19, 2000));
        Bet bet8 = new Bet(1000, "Can you make a sandwich in under 5 minutes? ", "How fast can you eat?", new User("John", 19, 2000));

        Model model = new Model(id++, 9,
                R.drawable.sample_profile_pic, R.drawable.sample_picture,
                "Ayush", "2 hrs", "Will Jets Win the game tonight?  .",
                bet2);

        models.add(model);

        model = new Model(id++,  6, R.drawable.download,
                0, "Justin", "9 hrs",
                "Can you use the terminal better than me?", bet3);
        models.add(model);

        model = new Model(id++, 17,  R.drawable.sample_picture,
                R.drawable.sample_picture, "Ray", "1 day",
                "Make 100 kills in fortnite by tomorrow. ", bet4);
        models.add(model);

        model = new Model(id++, 10,  R.drawable.betimage, 0,
                "Eddy", "9 hrs", "Can you solve this equation? ", bet5);
        models.add(model);

        model = new Model(id++, 107,  R.drawable.sample_profile_pic,
                R.drawable.sample_picture, "Kaitlyn", "13 hrs",
                "Can you beat me in a game in FIFA?", bet6);
        models.add(model);

        model = new Model(id++, 26,  R.drawable.download, 0,
                "Rob", "2 hrs", "Can you run 100m in 14 seconds? ", bet7);
        models.add(model);

        model = new Model(id++, 7, R.drawable.sample_picture,
                R.drawable.sample_picture, "John", "13 hrs",
                "Can you make a sandwich in under 5 minutes? ", bet8);
        models.add(model);
    }

    public ArrayList<Model> getModelSequential() {
        return models;
    }


    public Model insertModel(Model currentModel) {
        models.add(currentModel);
        id++;
        return currentModel;
    }


    public Model updateModel(Model currentModel) {
        int index;

        index = models.indexOf(currentModel);
        if(index>=0) {
            models.set(index,currentModel);
        }
        return currentModel;
    }

    public boolean deleteModel(Model currentModel) {
        int index;
        index = models.indexOf(currentModel);
        if(index>=0) {
            models.remove(index);
            return true;
        }
        return false;
    }

    public int getId(){return this.id;}

}
