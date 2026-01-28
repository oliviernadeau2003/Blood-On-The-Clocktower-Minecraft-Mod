package mrskyzz.botc.item.custom;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class DeathOverlayHelmetItem extends ArmorItem {

    public DeathOverlayHelmetItem(ArmorMaterial material, Properties properties) {
        super(material, Type.HELMET, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            @Override
            public HumanoidModel<?> getHumanoidArmorModel(
                    LivingEntity entity,
                    ItemStack stack,
                    EquipmentSlot slot,
                    HumanoidModel<?> original
            ) {
                // Hide ALL parts safely
                original.head.visible = false;
                original.hat.visible = false;
                original.body.visible = false;
                original.leftArm.visible = false;
                original.rightArm.visible = false;
                original.leftLeg.visible = false;
                original.rightLeg.visible = false;

                return original;
            }
        });
    }
}
