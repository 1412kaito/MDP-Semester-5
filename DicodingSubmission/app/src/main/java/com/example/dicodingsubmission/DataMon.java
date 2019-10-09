package com.example.dicodingsubmission;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataMon {
    private static String[] dummyData = {
            "Bulbasaur", "Ivysaur", "Venusaur",
            "Charmander", "Charmeleon", "Charizard",
            "Squirtle", "Wartortle", "Blastoise",
            "Chikorita", "Bayleef", "Meganium",
            "Cyndaquil", "Quilava", "Typhlosion",
            "Totodile", "Croconaw", "Feraligatr"
    };
    private static String[] dummyDetails = {
            "BULBASAUR can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun’s rays, the seed grows progressively larger.", "To support its bulb, IVYSAUR’s legs grow sturdy. If it spends more time lying in the sunlight, the bud will soon bloom into a large flower.", "VENUSAUR’s flower is said to take on vivid colors if it gets plenty of nutrition and sunlight. The flower’s aroma soothes the emotions of people.",
            "The flame that burns at the tip of its tail is an indication of its emotions. The flame wavers when Charmander is enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.", "Charmeleon mercilessly destroys its foes using its sharp claws. If it encounters a strong foe, it turns aggressive. In this excited state, the flame at the tip of its tail flares with a bluish white color.", "Charizard flies around the sky in search of powerful opponents. It breathes fire of such great heat that it melts anything. However, it never turns its fiery breath on any opponent weaker than itself.",
            "Squirtle’s shell is not merely used for protection. The shell’s rounded shape and the grooves on its surface help minimize resistance in water, enabling this Pokémon to swim at high speeds.", "Its tail is large and covered with a rich, thick fur. The tail becomes increasingly deeper in color as Wartortle ages. The scratches on its shell are evidence of this Pokémon’s toughness as a battler.", "Blastoise has water spouts that protrude from its shell. The water spouts are very accurate. They can shoot bullets of water with enough accuracy to strike empty cans from a distance of over 160 feet.",
            "In battle, Chikorita waves its leaf around to keep the foe at bay. However, a sweet fragrance also wafts from the leaf, becalming the battling Pokémon and creating a cozy, friendly atmosphere all around.", "Bayleef’s neck is ringed by curled-up leaves. Inside each tubular leaf is a small shoot of a tree. The fragrance of this shoot makes people peppy.", "The fragrance of Meganium’s flower soothes and calms emotions. In battle, this Pokémon gives off more of its becalming scent to blunt the foe’s fighting spirit.",
            "Cyndaquil protects itself by flaring up the flames on its back. The flames are vigorous if the Pokémon is angry. However, if it is tired, the flames splutter fitfully with incomplete combustion.", "Quilava keeps its foes at bay with the intensity of its flames and gusts of superheated air. This Pokémon applies its outstanding nimbleness to dodge attacks even while scorching the foe with flames.", "Typhlosion obscures itself behind a shimmering heat haze that it creates using its intensely hot flames. This Pokémon creates blazing explosive blasts that burn everything to cinders.",
            "Despite the smallness of its body, Totodile’s jaws are very powerful. While the Pokémon may think it is just playfully nipping, its bite has enough power to cause serious injury.", "Once Croconaw has clamped its jaws on its foe, it will absolutely not let go. Because the tips of its fangs are forked back like barbed fishhooks, they become impossible to remove when they have sunk in.", "Feraligatr intimidates its foes by opening its huge mouth. In battle, it will kick the ground hard with its thick and powerful hind legs to charge at the foe at an incredible speed."
    };
    private static int[] dummyDex = {
            1,2,3,4,5,6,7,8,9,
            152,153,154, 155,156,157, 158,159,160
    };
    private static int[] dummyImage = {
            R.drawable.bulbasaur, R.drawable.ivysaur, R.drawable.venusaur,
            R.drawable.charmander, R.drawable.charmeleon, R.drawable.charizard,
            R.drawable.squirtle, R.drawable.wartortle, R.drawable.blastoise,
            R.drawable.chikorita, R.drawable.bayleef, R.drawable.meganium,
            R.drawable.cyndaquil, R.drawable.quilava, R.drawable.typhlosion,
            R.drawable.totodile, R.drawable.croconaw, R.drawable.feraligatr
    };

    public static ArrayList<Pokemon> isi(){
        ArrayList<Pokemon> t = new ArrayList<>(dummyData.length);
        for (int i = 0; i < dummyData.length; i++) {
            t.add(new Pokemon(dummyDetails[i], dummyData[i], dummyDex[i], dummyImage[i]));
        }
        return t;
    }
}
