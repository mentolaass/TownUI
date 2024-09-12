package ru.mentola.townui.core.component.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.DrawContext;
import ru.mentola.townui.core.component.Component;
import ru.mentola.townui.core.model.Model;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;

import java.util.Set;

@Getter @Setter
public final class ListComponent extends Component {
    private Set<? extends Model> models;

    @Override
    public void render(DrawContext context, Mouse mouse, float delta) {
        super.render(context, mouse, delta);
    }

    @Override
    public void mouseClicked(Mouse mouse, MouseButton button) {
        super.mouseClicked(mouse, button);
    }

    @Override
    public void keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
    }

    //public static ListComponentBuilder builder(ComponentBuilder<ListComponent> builder) {
        //return new ListComponentBuilder(builder.build());
    //}

    //@AllArgsConstructor
    //public static class ListComponentBuilder {
    //    private final ListComponent instance;

    //    public ListComponentBuilder setModels(Set<? extends Model> models) {
    //        instance.setModels(models);
    //        return this;
    //    }

    //    public ListComponent build() {
    //        return this.instance;
    //    }
    //}
}