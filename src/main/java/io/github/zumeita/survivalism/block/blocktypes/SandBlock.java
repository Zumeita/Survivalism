package io.github.zumeita.survivalism.block.blocktypes;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class SandBlock extends Block {

    public SandBlock() {
        super(Properties.of(Material.SAND, MaterialColor.SAND).strength(0.5F).sound(SoundType.SAND));
    }
}
