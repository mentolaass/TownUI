package ru.mentola.example.testscreen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import ru.mentola.townui.api.provider.TownUIProvider;
import ru.mentola.townui.core.component.common.*;
import ru.mentola.townui.core.image.Image;
import ru.mentola.townui.core.layer.Layer;
import ru.mentola.townui.core.mouse.Mouse;
import ru.mentola.townui.core.mouse.MouseButton;
import ru.mentola.townui.core.positioning.HorizontalAlignment;
import ru.mentola.townui.core.positioning.Padding;
import ru.mentola.townui.core.positioning.VerticalAlignment;
import ru.mentola.townui.core.processor.ProcessType;
import ru.mentola.townui.core.property.Properties;
import ru.mentola.townui.core.property.provider.PropertyProvider;

import java.awt.*;

public final class TestScreen extends Screen {
    private final Layer layer;

    public TestScreen() {
        super(Text.of("Test"));

        this.layer = new Layer(0, 0, 0, 0, true);

        this.layer
                .addComponent(TownUIProvider.getAPI().getComponentBuilder(BlockComponent.class)
                        .putProperty(PropertyProvider.provide(Properties.BACKGROUND_COLOR, new Color(15, 15, 15)))
                        .setAutosize(true)
                        .setX(0)
                        .setY(0)
                        .setName("background-block")
                        .build())
                .addComponent(TownUIProvider.getAPI().getComponentBuilder(BlockComponent.class)
                        .setX(20)
                        .setY(20)
                        .setHeight(40)
                        .setName("title-block")
                        .putProperty(PropertyProvider.provide(Properties.BACKGROUND_COLOR, new Color(25, 25, 25)))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_COLOR, new Color(45, 45, 45)))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_THICKNESS, 1.0))
                        .setProcessor((component, type, args) -> {
                            if (component instanceof BlockComponent block
                                    && block.getName().equals("title-block")
                                    && type == ProcessType.RESIZE) {
                                MinecraftClient client = MinecraftClient.getInstance();
                                double width = client.getWindow().getScaledWidth();
                                block.setWidth(width - 40);
                            }
                        })
                        .build())
                .addComponent(TownUIProvider.getAPI().getComponentBuilder(LabelComponent.class)
                        .setX(20)
                        .setY(20)
                        .setHeight(40)
                        .putProperty(PropertyProvider.provide(Properties.FOREGROUND_COLOR, Color.WHITE))
                        .putProperty(PropertyProvider.provide(Properties.AUTO_WRAP, true))
                        .putProperty(PropertyProvider.provide(Properties.CONTENT_PADDING, new Padding(5, 0, 5, 0)))
                        .putProperty(PropertyProvider.provide(Properties.CONTENT, "Lorem ipsum odor amet, consectetuer adipiscing elit. Venenatis tempor sapien egestas faucibus consectetur nostra ipsum. Pellentesque eleifend tempor venenatis varius ridiculus mi felis."))
                        .setName("title-text")
                        .setProcessor((component, type, args) -> {
                            if (component instanceof LabelComponent label
                                    && label.getName().equals("title-text")
                                    && type == ProcessType.RESIZE) {
                                MinecraftClient client = MinecraftClient.getInstance();
                                double width = client.getWindow().getScaledWidth();
                                label.setWidth(width - 40);
                            }
                        })
                        .build())
                .addComponent(TownUIProvider.getAPI().getComponentBuilder(ImageComponent.class)
                        .setX(20)
                        .setY(70)
                        .setHeight(60)
                        .setWidth(100)
                        .putProperty(PropertyProvider.provide(Properties.IMAGE, new Image(Identifier.of("townui", "/assets/townui/gif/gif.gif"))))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_COLOR, new Color(45, 45, 45)))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_THICKNESS, 1.0))
                        .putProperty(PropertyProvider.provide(Properties.DEBUG, true))
                        .build())
                .addComponent(TownUIProvider.getAPI().getComponentBuilder(ButtonComponent.class)
                        .setX(20)
                        .setY(140)
                        .setHeight(30)
                        .setWidth(100)
                        .setName("button-test")
                        .putProperty(PropertyProvider.provide(Properties.CONTENT, "Test btn lol xd"))
                        .putProperty(PropertyProvider.provide(Properties.CONTENT_VERTICAL_ALIGNMENT, VerticalAlignment.CENTER))
                        .putProperty(PropertyProvider.provide(Properties.CONTENT_HORIZONTAL_ALIGNMENT, HorizontalAlignment.CENTER))
                        .putProperty(PropertyProvider.provide(Properties.BACKGROUND_COLOR, new Color(25, 25, 25)))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_COLOR, new Color(45, 45, 45)))
                        .putProperty(PropertyProvider.provide(Properties.BORDER_THICKNESS, 1.0))
                        .setProcessor((component, type, args) -> {
                            if (component instanceof ButtonComponent button
                                    && button.getName().equals("button-test")) {
                                if (type == ProcessType.MOUSE_ENTER) {
                                    button.setBorderColor(new Color(65, 65, 65));
                                } else if (type == ProcessType.MOUSE_LEAVE) {
                                    button.setBorderColor(new Color(45, 45, 45));
                                }
                            }
                        })
                        .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        layer.render(context, new Mouse(mouseX, mouseY), delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        layer.mouseClicked(new Mouse(mouseX, mouseY), button == 0 ? MouseButton.LEFT : button == 1 ? MouseButton.RIGHT : MouseButton.MIDDLE);
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void resize(MinecraftClient client, int width, int height) {
        layer.resize();
        super.resize(client, width, height);
    }
}
