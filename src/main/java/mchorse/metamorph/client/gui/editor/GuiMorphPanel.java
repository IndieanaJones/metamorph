package mchorse.metamorph.client.gui.editor;

import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.metamorph.api.morphs.AbstractMorph;
import net.minecraft.client.Minecraft;

public class GuiMorphPanel<T extends AbstractMorph> extends GuiElement
{
    public GuiAbstractMorph editor;
    public T morph;

    public GuiMorphPanel(Minecraft mc, GuiAbstractMorph editor)
    {
        super(mc);
        this.createChildren();
    }

    public void startEditing()
    {}

    public void finishEditing()
    {}

    public void fillData(T morph)
    {
        this.morph = morph;
    }
}