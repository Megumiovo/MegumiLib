package com.taylorswiftcn.megumi.megumilib.item.util

import com.taylorswiftcn.megumi.megumilib.MegumiLib
import com.taylorswiftcn.megumi.megumilib.regular.MathRegularUtil
import com.taylorswiftcn.megumi.megumilib.util.ColorUtil
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

class BuildItemUtil {
    companion object {

        private val plugin = MegumiLib.getInstance()

        fun create(_id: String, _data: Int, _amount: Int): ItemStack {
            return create(_id, _data, _amount, null, null)
        }

        fun create(_id: String, _data: Int, _amount: Int, _name: String?, _lore: List<String>?): ItemStack {
            var id = _id
            var data = _data
            var amount = _amount
            var name = _name
            var lore = _lore

            var item: ItemStack? = null

            if (MathRegularUtil.isPositiveInt(id)) {
                if (plugin.getVersion().startsWith("V1_13")) {
                    item = ItemStack(Material.STONE, 1)
                    name = "Please use the string ID"
                    lore = null
                }
                else {
                    item = ItemStack(id.toInt(), amount)
                }
            }
            else {
                item = ItemStack(Material.getMaterial(id), amount)
            }

            item.durability = data.toShort()

            var meta: ItemMeta = item.itemMeta

            if (name != null) {
                meta.displayName = ColorUtil.replace(name)
            }

            if (lore != null) {
                meta.lore = ColorUtil.replace(lore)
            }

            item.itemMeta = meta

            return item
        }
    }
}