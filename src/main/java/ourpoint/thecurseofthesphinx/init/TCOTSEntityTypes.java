package ourpoint.thecurseofthesphinx.init;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import ourpoint.thecurseofthesphinx.TheCurseOfTheSphinx;
import ourpoint.thecurseofthesphinx.entity.MummyEntity;
import ourpoint.thecurseofthesphinx.entity.ScarabEntity;
import ourpoint.thecurseofthesphinx.entity.item.ToiletPaperEntity;

public class TCOTSEntityTypes
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, TheCurseOfTheSphinx.MOD_ID);

    public static final RegistryObject<EntityType<MummyEntity>> MUMMY_ENTITY = ENTITY_TYPES.register("mummy",
            () -> EntityType.Builder.create(MummyEntity::new, EntityClassification.MONSTER)
                .size(0.6F, 1.95F)
                .build(new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "mummy").toString()));

    public static final RegistryObject<EntityType<ScarabEntity>> SCARAB_ENTITY = ENTITY_TYPES.register("scarab",
            () -> EntityType.Builder.create(ScarabEntity::new, EntityClassification.CREATURE)
                .size(0.8F, 0.5F)
                .build(new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "scarab").toString()));

    public static final RegistryObject<EntityType<ToiletPaperEntity>> TOILET_PAPER_ENTITY = ENTITY_TYPES.register("toilet_paper",
            () -> EntityType.Builder.<ToiletPaperEntity>create(ToiletPaperEntity::new, EntityClassification.MISC)
                .size(0.25F, 0.25F)
                .build(new ResourceLocation(TheCurseOfTheSphinx.MOD_ID, "toilet_paper").toString())
    );
}
