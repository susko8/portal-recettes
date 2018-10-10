package com.recettes.portalrecettes;

import com.recettes.portalrecettes.models.*;
import com.recettes.portalrecettes.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import javax.imageio.ImageIO;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class PortalRecipesApplication {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IngredientDao ingredientDao;
    @Autowired
    private RecipeDao recetteDao;


    public static void main(String[] args) {
        SpringApplication.run(PortalRecipesApplication.class, args);
    }

   /* public void addIngredientToUser(User user, String nomIngredient) {
        Iterable<Ingredient> listIngredients = ingredientDao.findAll();
        for (Ingredient i : listIngredients) {
            if (i.getNom().equals(nomIngredient)) {
                user.getIngredients().add(i);
                userDao.save(user);
                return;
            }
        }
        System.out.println("ingredient non supporté !");

    }

    public void addListIngredientToUser(User user, List<Ingredient> ingredients) {
        for (Ingredient i : ingredients) {
            addIngredientToUser(user, i.getNom());
        }

    }*/

    // Function used to save contents in the DB
    // Save a Recipe and it's list of ingredient
    // Eventually add this list to user's ingredients passed as argument
    public void addDataset(Recipes recette, List<Ingredient> ingredients, User[] users) {

        ingredientDao.saveAll(ingredients);
        recette.setIngredient(ingredients);
        recetteDao.save(recette);

        for (User i : users) {
            i.setIngredients(ingredients);
            userDao.save(i);
        }


    }

    @PostConstruct
    public void init() {

        // Creation of an user account
        User us1 = new User("quentin@gmail.com", "miam", "quentin", "unal");
        User us2 = new User("laurine@gmail.com", "1234", "laurine", "torossian");
        User us3 = new User("sophie@gmail.com", "tasty", "sophie", "aitis");
        User us4 = new User("samuel@gmail.com", "12345", "samuel", "susoliak");
        User us5 = new User("ambroise@gmail.com", "12345", "ambroise", "asoullier");


        // Creation of ingredients list for receipe

        // Cheese omelet
        List<Ingredient> listOmletFrmg = new ArrayList<>();

        listOmletFrmg.add(new Ingredient("oeuf", "https://www.journee-mondiale.com/medias/images/journee/oeuf.png"));
        listOmletFrmg.add(new Ingredient("fromage rapé", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_S2NXyBABiaXbdd-FbdeV9LuY0EwtowXdk6gyYS3ikWsV8mda"));

        // Pasta carbonara
        List<Ingredient> listCarbo = new ArrayList<>();

        listCarbo.add(new Ingredient("parmesan", "https://www.regal.fr/sites/art-de-vivre/files/styles/large/public/r_parmesan-istock.jpg?itok=Q6TTgV2O"));
        listCarbo.add(new Ingredient("pates", "https://www.pastadelices.fr/43/fusilli-nature-fraiches.jpg"));
        listCarbo.add(new Ingredient("lardons", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Lardons.jpg/1200px-Lardons.jpg"));
        listCarbo.add(new Ingredient("crème fraiche", "https://www.plaisirslaitiers.ca/var/ezflow_site/storage/images/dairy-goodness/home/recipes/creme-fraiche/258517-8-eng-CA/creme-fraiche_large.jpg"));
        listCarbo.add(new Ingredient("échalottes", "https://img-3.journaldesfemmes.com/6zLXXihuCjLfNwwgLCk2Abtn7u0=/910x607/smart/913a2e9520104564b6011257a8fd1181/ccmcms-jdf/10663185.jpg"));

        // Salad of red fruits
        List<Ingredient> listSldFrtRg = new ArrayList<>();

        listSldFrtRg.add(new Ingredient("fraises", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgE_ZdRddZA-yB02wsybNpNxUI-L-w7VfiDewMhgbZSRUI1m9R"));
        listSldFrtRg.add(new Ingredient("framboises", "https://img-3.journaldesfemmes.com/lZqm2rIC2PMKPP1zVrNP7uFsyoo=/910x607/smart/91eed6d1ec5c4912849bc192c62161ab/ccmcms-jdf/10662426.jpg"));
        listSldFrtRg.add(new Ingredient("sucre", "https://www.rts.ch/2014/07/10/13/03/5196226.image?w=800&h=449"));

        // Cake

        List<Ingredient> listQQuart = new ArrayList<>();

        listQQuart.add(new Ingredient("beurre", "https://media.toupargel.fr/p-565x436/3364-2-beurre-de-printemps-surgele-doux-250-g-30944.jpg"));
        listQQuart.add(new Ingredient("farine", "https://www.academiedugout.fr/images/9597/370-274/ffffff/fotolia_26017871_subscription_l.jpg?poix=50&poiy=50"));
        listQQuart.add(new Ingredient("oeuf", "https://www.journee-mondiale.com/medias/images/journee/oeuf.png"));
        listQQuart.add(new Ingredient("sucre", "https://www.rts.ch/2014/07/10/13/03/5196226.image?w=800&h=449"));

        // Concumber with cream
        List<Ingredient> listCCreme = new ArrayList<>();

        listCCreme.add(new Ingredient("concombres", "https://www.auchandirect.fr/backend/media/products_images/0N_57352.jpg"));
        listCCreme.add(new Ingredient("creme fraiche", "https://www.plaisirslaitiers.ca/var/ezflow_site/storage/images/dairy-goodness/home/recipes/creme-fraiche/258517-8-eng-CA/creme-fraiche_large.jpg"));
        listCCreme.add(new Ingredient("vinaigre", "https://media.toupargel.fr/p-565x436/5220-2-vinaigre-de-vin-amora-75-cl-15883.jpg"));

        // Tuna/Avocado toast

        List<Ingredient> listToast = new ArrayList<>();

        listToast.add(new Ingredient("pain", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQz6Q9qH1rr9IMDG3i4Mrr0_SB7CmeOIlVitP9TXiwaFaF3ZUxtBg"));
        listToast.add(new Ingredient("mayonnaise", "https://image.afcdn.com/recipe/20161128/29315_w1024h768c1cx1781cy1358.jpg"));
        listToast.add(new Ingredient("thon", "https://st.depositphotos.com/1067820/5019/i/950/depositphotos_50198895-stock-photo-flaked-tuna-pieces-in-white.jpg"));


        // Puree with carotts

        List<Ingredient> listPuree = new ArrayList<>();

        listPuree.add(new Ingredient("pomme de terre", "https://www.comptoirdesjardins.fr/2058-thickbox_default/plants-de-pommes-de-terre-bio-passion-3-kg.jpg"));
        listPuree.add(new Ingredient("carotte", "https://img-3.journaldesfemmes.com/4PYoBAo1J5rsDbuMiSTNRGolvhc=/910x607/smart/3b81125b1cbd46f7af87766bb8430152/ccmcms-jdf/10659145.jpg"));
        listPuree.add(new Ingredient("creme fraiche", "https://www.plaisirslaitiers.ca/var/ezflow_site/storage/images/dairy-goodness/home/recipes/creme-fraiche/258517-8-eng-CA/creme-fraiche_large.jpg"));

        // Receipe creation

        Recipes r1 = new Recipes("Omellette au fromage", "Battre les oeufs.\n" +
                "Faire cuire les oeuf battus dans une poêle.\n" +
                "Ajouter le fromage rapé.\n" +
                "Retirer du feu.\n", "https://cac.img.pmdstatic.net/fit/http.3A.2F.2Fprd2-bone-image.2Es3-website-eu-west-1.2Eamazonaws.2Ecom.2Fcac.2F2018.2F09.2F25.2F6d1aef0b-b0ad-4c4c-b670-63f975b72c88.2Ejpeg/748x372/quality/80/crop-from/center/omelette-au-fromage.jpeg");


        Recipes r2 = new Recipes("Pates carbonara", "Cuire les pâtes.\n" +
                "Emincer les échalottes et les faire revenir à la poêle avec les lardons.\n" +
                "Ajouter la crème.\n" +
                "Une fois les pâtes cuites, incorporer la crème. Saupoudrer de parmesan. ", "https://static.750g.com/images/622-auto/f6ad72f2ac5f330143bd9bc27566dee6/comment-realiser-des-pates-carbonara-comme-en-italie.jpg");

        Recipes r3 = new Recipes("Salade de fruits rouges", "Mélangez les fraises et les framboises avec le sucre. Laissez reposer 3h au frigo.. \n", "https://cache.magicmaman.com/data/photo/w600_c18/4a/salade-de-fruits-rouges.jpg");

        Recipes r4 = new Recipes("Quatre quarts", "Mélangez 140g de sucre, 140g de farine, 140g de beurre et 3 oeufs. Faites cuire 10 min à 180 degrés", "https://static.cuisineaz.com/610x610/i2632-quatre-quart-facile.jpeg");

        Recipes r5 = new Recipes("Concombres à la crème", "Epluchez les concombres et coupez les en rondelles. Préparez la sauce en mélangeant la crème et le vinaigre. Ajoutez la sauce aux concombres et laissez au frigo pendant 3h", "https://www.academiedugout.fr/images/14097/948-580/fotolia_60657745_subscription_xxl.jpg?poix=50&poiy=50");

        Recipes r6 = new Recipes("Toast Thon/avocat", "D'une part, emiettez le thon, et mélangez le avec de la mayonnaise. D'autre part, écrasez la chair d'avocat. Mélanger les deux puis tartinez le pain avec", "https://image.afcdn.com/recipe/20160725/31500_w420h344c1cx2100cy1226.jpg");

        Recipes r7 = new Recipes("Purée de carottes", "D'une part, épluchez et faites cuire les carottes et les pommes de terre. Ecrasez le tout à la fourchette. Ajoutez la crème fraiche dans le mélange.", "https://www.cookomix.com/wp-content/uploads/2017/09/puree-carotte-patate-douce-thermomix-800x600.jpg");

        //Add of dataset

        addDataset(r1, listOmletFrmg, new User[]{us1, us4});
        addDataset(r2, listCarbo, new User[]{us3, us2});
        addDataset(r3, listSldFrtRg, new User[]{us3, us2});
        addDataset(r4, listQQuart, new User[]{us3, us2});
        addDataset(r5, listCCreme, new User[]{us3, us2});
        addDataset(r6, listToast, new User[]{us3, us2});
        addDataset(r7, listToast, new User[]{us3, us2});
        addDataset(r7,listToast,new User[]{us5});
        addDataset(r7,listCCreme,new User[]{us5});
        addDataset(r7,listOmletFrmg,new User[]{us5});


        try {
            Images im =new Images();
            (new File(".\\src\\main\\resources\\static\\img\\hello.png")).createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
