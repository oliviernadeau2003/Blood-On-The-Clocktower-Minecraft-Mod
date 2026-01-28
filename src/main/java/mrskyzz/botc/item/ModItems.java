package mrskyzz.botc.item;

import mrskyzz.botc.Botc;
import mrskyzz.botc.item.custom.BotcBookItem;
import mrskyzz.botc.item.custom.DeathOverlayHelmetItem;
import mrskyzz.botc.item.custom.RoleBookItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Botc.MOD_ID);

    public static final RegistryObject<Item> BOTC_BOOK = ITEMS.register("botc_book",
            () -> new BotcBookItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    // ROLES DEMONS ---
    // Register books

    public static final RegistryObject<Item> BOOK_ROLE_DIABLOTIN = ITEMS.register(
            "book_role_diablotin",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Diablotin",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",
                                      {"text":"Role : Diablotin","bold":true,"color":"red"},
                                      {"text":"\\n","color":"red"},
                                      {"text":"Camp : Démons","bold":true,"color":"red"},
                                      {"text":"\\n\\nTu es le chef des ","color":"reset"},
                                      {"text":"Démons","bold":true,"color":"red"},
                                      {"text":", chaque nuit tu choisis de tuer quelqu'un. Tu auras 3 ","color":"reset"},
                                      {"text":"Roles","bold":true,"color":"dark_blue"},
                                      {"text":" qui ne sont pas dans la composition a toi de decider lequel\\ntu préfère fake.","color":"reset"}
                                    ]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",
                                      {"text":"Tu peux te viser toi même la nuit et un de tes sbires aléatoire("},
                                      {"text":"Femme Écarlate prioritère","bold":true,"color":"red"},
                                      {"text":") récuperas alors le rôle de ","color":"reset"},
                                      {"text":"diablotin","bold":true,"color":"red"},
                                      {"text":". ","color":"reset"}
                                    ]
                                    """))
                    )
            )
    );

    public static final RegistryObject<Item> BOOK_ROLE_BARON = ITEMS.register(
            "book_role_baron",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Baron",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Baron","bold":true,"color":"red"},{"text":"\\n","color":"red"},
                                    {"text":"Camp : Démons","bold":true,"color":"red"},
                                    {"text":"\\n\\nTu es l'un des ","color":"reset"},
                                    {"text":"Sbires ","bold":true,"color":"red"},
                                    {"text":"du ","color":"black"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":",\\nton objectif est qu'il reste en vie. Tu n'as aucun pouvoir mais\\nton ","color":"black"},
                                    {"text":"Role","bold":true,"color":"dark_blue"},
                                    {"text":" ajoute deux ","color":"black"},
                                    {"text":"Roles Étranger ","bold":true,"color":"dark_blue"},
                                    {"text":"a la composition.","color":"black"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Lors de la premiere \\nnuit je vais te donner\\nle nom de ton "},
                                    {"text":"Diablotin","bold":true,"color":"red"},{"text":"\\n","color":"reset"},
                                    {"text":"ainsi que ceux des autres ","color":"black"},
                                    {"text":"Sbires","bold":true,"color":"red"},{"text":".","bold":true}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_EMPOISONNEUR = ITEMS.register("book_role_empoisonneur",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_EMPOISONNEUR = ITEMS.register(
            "book_role_empoisonneur",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Empoisonneur",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Empoisoneur\\nCamp : Démons","bold":true,"color":"red"},
                                    {"text":"\\n\\nTu es l'un des ","color":"black"},
                                    {"text":"Sbires","bold":true,"color":"red"},
                                    {"text":"\\ndu ","color":"black"},
                                    {"text":"Diablotin. ","bold":true,"color":"red"},
                                    {"text":"\\nChaque nuit tu peux empoisoner quelqu'un.","color":"reset"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Lors de la premiere \\nnuit je vais te donner\\nle nom de ton "},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":"\\nainsi que ceux des autres "},
                                    {"text":"Sbires","bold":true,"color":"red"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_ESPION = ITEMS.register("book_role_espion",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_ESPION = ITEMS.register(
            "book_role_espion",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Espion",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Espion\\nCamp : Démons","bold":true,"color":"red"},
                                    {"text":"\\n\\nTu es l'un des ","color":"black"},
                                    {"text":"Sbires","bold":true,"color":"red"},
                                    {"text":" du Diablotin. ","bold":true,"color":"red"},
                                    {"text":"La première nuit tu receveras tous les ","color":"black"},
                                    {"text":"Roles","bold":true,"color":"dark_blue"},
                                    {"text":" de la partie.","color":"black"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Tu appraise comme "},
                                    {"text":"Citadin","bold":true,"color":"dark_blue"},
                                    {"text":" aux yeux des rôles à information."}]
                                    """))
                    )
            )
    );


    //    public static final RegistryObject<Item> BOOK_ROLE_FEMME_ECARLATE = ITEMS.register("book_role_femme_ecarlate",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_FEMME_ECARLATE = ITEMS.register(
            "book_role_femme_ecarlate",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Femme Écarlate",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Femme Écarlate\\nCamp : Démons","bold":true,"color":"red"},
                                    {"text":"\\n\\nSi ton ","color":"black"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" meurt avec 5 joueurs ou plus en vie, tu prends sa place.","color":"black"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Protège ton "},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" à tout prix."}]
                                    """))
                    )
            )
    );

    // ROLES CITADINS ---

    //    public static final RegistryObject<Item> BOOK_ROLE_ARCHIVISTE = ITEMS.register("book_role_archiviste",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_ARCHIVISTE = ITEMS.register(
            "book_role_archiviste",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Archiviste",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Archiviste","bold":true,"color":"dark_blue"},
                                    {"text":"\\nCamp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nLa première nuit, deux joueurs et un rôle Étranger te seront indiqués."}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_CROQUE_MORT = ITEMS.register("book_role_croque_mort",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_CROQUE_MORT = ITEMS.register(
            "book_role_croque_mort",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Croque-Mort",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Croque-Mort","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nSi tu meurt la nuit, Lors du réveille\\ntu pourra désigner\\nun joueur dont tu apprendra l'identité.\\ntu peut désigner\\nun joueur mort.\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_CUISTOT = ITEMS.register("book_role_cuistot",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_CUISTOT = ITEMS.register(
            "book_role_cuistot",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Cuistot",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Cuistot","bold":true,"color":"dark_blue"},
                                    {"text":"\\nCamp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nTu apprends le nombre de paires de ","color":"reset"},
                                    {"text":"Démons","bold":true,"color":"red"},
                                    {"text":" autour de la table."}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Deux démons côte à côte = 1 paire."}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_EMPATHE = ITEMS.register("book_role_empathe",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_EMPATHE = ITEMS.register(
            "book_role_empathe",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Empathe",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Empathe","bold":true,"color":"dark_blue"},
                                    {"text":"\\nCamp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nChaque nuit, tu apprends le nombre de méchants à côté de toi."}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_ENQUETEUR = ITEMS.register("book_role_enqueteur",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_ENQUETEUR = ITEMS.register(
            "book_role_enqueteur",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Enquêteur",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Enquêteur","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citations","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nLa première nuit, deux joueurs ainsi qu'un rôle ","color":"reset"},
                                    {"text":"Sbire","bold":true,"color":"red"},
                                    {"text":" spécifique vont t'être indiqués. Parmi ces deux joueurs se trouve ce rôle ","color":"reset"},
                                    {"text":"Sbire","bold":true,"color":"red"},
                                    {"text":".\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );


    //    public static final RegistryObject<Item> BOOK_ROLE_FOSSOYEUR = ITEMS.register("book_role_fossoyeur",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_FOSSOYEUR = ITEMS.register(
            "book_role_fossoyeur",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Fossoyeur",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Fossoyeur","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nChaque nuit, si un joueur a été exécuté au vote lors de cette journée, Tu apprendra l'identité (","color":"reset"},
                                    {"text":"Role","bold":true,"color":"dark_blue"},
                                    {"text":") de l'exécuté.\\n\\n ","color":"reset"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Si le joueur exécuté était "},
                                    {"text":"l'Ivrogne","bold":true,"color":"dark_blue"},
                                    {"text":", Tu versa le rôle ","color":"reset"},
                                    {"text":"d'Ivrogne","bold":true,"color":"dark_blue"},
                                    {"text":" et non le faux "},
                                    {"text":"Rôle","bold":true,"color":"dark_blue"},
                                    {"text":" attribué.","color":"reset"}]
                                    """))
                    )
            )
    );


    //    public static final RegistryObject<Item> BOOK_ROLE_LAVANDIERE = ITEMS.register("book_role_lavandiere",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_LAVANDIERE = ITEMS.register(
            "book_role_lavandiere",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Lavandière",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Lavandière","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nLa première nuit, deux joueurs ainsi qu'un rôle ","color":"reset"},
                                    {"text":"Citadin","bold":true,"color":"dark_blue"},
                                    {"text":" spécifique vont être indiqués. Parmi ces deux joueurs se trouve\\nce rôle ","color":"reset"},
                                    {"text":"Citadin","bold":true,"color":"dark_blue"},
                                    {"text":".\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );


    //    public static final RegistryObject<Item> BOOK_ROLE_MAIRE = ITEMS.register("book_role_maire",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_MAIRE = ITEMS.register(
            "book_role_maire",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Maire",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Maire","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nSi le tu es ciblé par le ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" la nuit, un autre joueur PEUT mourir à ta place.\\n\\n ","color":"reset"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Lorsqu'il reste trois joueurs en vie, si la journée se termine sans "},
                                    {"text":"exécution","color":"dark_red"},
                                    {"text":", les ","color":"reset"},
                                    {"text":"Citadins","bold":true,"color":"dark_blue"},
                                    {"text":" gagnent instantanément.","color":"reset"}]
                                    """))
                    )
            )
    );


    //    public static final RegistryObject<Item> BOOK_ROLE_MOINE = ITEMS.register("book_role_moine",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_MOINE = ITEMS.register(
            "book_role_moine",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Moine",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Moine","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nChaque nuit, tu choisis un joueur autre que toi-même. Il ne peut pas mourir par le ","color":"reset"},
                                    {"text":"Démon","bold":true,"color":"red"},
                                    {"text":" cette nuit.\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_POURFENDEUR = ITEMS.register("book_role_pourfendeur",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_POURFENDEUR = ITEMS.register(
            "book_role_pourfendeur",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Pourfendeur",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Pourfendeur","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nUne fois par partie, pendant la journée, Tu peux désigner une cible aux yeux de tous. Si cette cible est le ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":", le ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" meurt. Si cette cible n'est pas le ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":", rien ne se passe.\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_SOLDAT = ITEMS.register("book_role_soldat",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_SOLDAT = ITEMS.register(
            "book_role_soldat",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Soldat",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Soldat","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nTu ne peux pas mourir du ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" la nuit. Si tu es ciblé, rien ne se passe.\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_VIERGE = ITEMS.register("book_role_vierge",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_VIERGE = ITEMS.register(
            "book_role_vierge",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Vierge",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Vierge","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nSi tu es nommée au vote et que le joueur qui ta designée est un ","color":"reset"},
                                    {"text":"Citadin","bold":true,"color":"dark_blue"},
                                    {"text":" (Étrangers exclus), ce joueur\\nest instantanément exécuté. Cette situation ne peut se produire qu'une fois.\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_VOYANTE = ITEMS.register("book_role_voyante",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_VOYANTE = ITEMS.register(
            "book_role_voyante",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Voyante",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Voyante","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n \\nChaque nuit, tu choisis deux joueurs. Tu apprendra alors si le ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" se trouve parmi les deux.\\n\\n\\n ","color":"reset"}]
                                    """)),
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Cependant, l'un des "},
                                    {"text":"Citadins","bold":true,"color":"dark_blue"},
                                    {"text":" (appeal\\nle \\"","color":"reset"},
                                    {"text":"Beurre","bold":true,"color":"dark_green"},
                                    {"text":"\\"), choisi aléatoirement en\\ndébut de partie par\\nle MJ, apparaîtra comme ","color":"reset"},
                                    {"text":"Diablotin","bold":true,"color":"red"},
                                    {"text":" pour\\ntoi tout au long de\\nla partie. tu peux te choisir toi-même ainsi que des joueurs morts.","color":"reset"}]
                                    """))
                    )
            )
    );

    //    public static final RegistryObject<Item> BOOK_ROLE_RECLUS = ITEMS.register("book_role_reclus",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_RECLUS = ITEMS.register(
            "book_role_reclus",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Reclus",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Reclus","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nTu es un ","color":"reset"},
                                    {"text":"Étranger","bold":true,"color":"dark_green"},
                                    {"text":"\\nton but reste de\\ngagner avec\\nles ","color":"reset"},
                                    {"text":"Citadins","bold":true,"color":"dark_blue"},
                                    {"text":",\\nCependant tu\\napparais en tant\\nque mechant au\\nyeux des roles\\na information","color":"black"},
                                    {"text":"\\n \\n\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    // ROLES ÉTRANGERS ---

    //    public static final RegistryObject<Item> BOOK_ROLE_IVROGNE = ITEMS.register("book_role_ivrogne",
//            ()-> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));

    //    public static final RegistryObject<Item> BOOK_ROLE_SAINT = ITEMS.register("book_role_saint",
//            () -> new Item(new Item.Properties().rarity(Rarity.RARE).stacksTo(1)));
    public static final RegistryObject<Item> BOOK_ROLE_SAINT = ITEMS.register(
            "book_role_saint",
            () -> new RoleBookItem(
                    new Item.Properties().rarity(Rarity.RARE).stacksTo(1),
                    "Saint",
                    "iiiBag",
                    List.of(
                            Objects.requireNonNull(Component.Serializer.fromJson("""
                                    ["",{"text":"Role : Saint","bold":true,"color":"dark_blue"},
                                    {"text":"\\n","color":"reset"},
                                    {"text":"Camp : Citadins","bold":true,"color":"dark_blue"},
                                    {"text":"\\n\\nTu es un ","color":"reset"},
                                    {"text":"Étranger","bold":true,"color":"dark_green"},
                                    {"text":"\\nton but reste de gagner avec\\nles ","color":"reset"},
                                    {"text":"Citadins","bold":true,"color":"dark_blue"},
                                    {"text":", ","color":"black"},
                                    {"text":"Si tu es exécuté au vote, les Citadins perdent instantanément la partie.\\n \\n\\n\\n\\n ","color":"reset"}]
                                    """))
                    )
            )
    );

    public static final RegistryObject<Item> DEATH_PLAYER_HELMET = ITEMS.register(
            "death_player_helmet",
            () -> new DeathOverlayHelmetItem(ArmorMaterials.LEATHER, new Item.Properties())
    );

    // REGISTER
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
