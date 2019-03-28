package mchorse.metamorph.bodypart;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Body part manager
 * 
 * Simplifies management of body parts for mods that use it. Besides 
 * that it allows in the future to implement different types of body 
 * parts such as particle body part...
 * 
 * Damn, I want particle body parts...
 */
public class BodyPartManager
{
    /**
     * List of body parts (on client side only)
     */
    public List<BodyPart> parts = new ArrayList<BodyPart>();

    /**
     * Whether body parts were initiated 
     */
    private boolean initiated;

    /**
     * Reset initiated state
     */
    public void reset()
    {
        this.initiated = false;
    }

    @SideOnly(Side.CLIENT)
    public void reinitBodyParts()
    {
        this.reset();
        this.initBodyParts();
    }

    @SideOnly(Side.CLIENT)
    public void initBodyParts()
    {
        if (!this.initiated)
        {
            for (BodyPart part : this.parts)
            {
                part.init();
            }

            this.initiated = true;
        }
    }

    /**
     * Update body limbs 
     */
    @SideOnly(Side.CLIENT)
    public void updateBodyLimbs(EntityLivingBase target)
    {
        for (BodyPart part : this.parts)
        {
            part.update(target);
        }
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof BodyPartManager)
        {
            BodyPartManager manager = (BodyPartManager) obj;

            return this.parts.equals(manager.parts);
        }

        return super.equals(obj);
    }

    /* NBT */

    public NBTTagList toNBT()
    {
        if (!this.parts.isEmpty())
        {
            NBTTagList bodyParts = new NBTTagList();

            for (BodyPart part : this.parts)
            {
                NBTTagCompound bodyPart = new NBTTagCompound();

                part.toNBT(bodyPart);

                if (!bodyPart.hasNoTags())
                {
                    bodyParts.appendTag(bodyPart);
                }
            }

            return bodyParts;
        }

        return null;
    }

    public void fromNBT(NBTTagList bodyParts)
    {
        this.parts.clear();

        for (int i = 0, c = bodyParts.tagCount(); i < c; i++)
        {
            NBTTagCompound bodyPart = bodyParts.getCompoundTagAt(i);
            BodyPart part = new BodyPart();

            part.fromNBT(bodyPart);
            this.parts.add(part);
        }
    }
}