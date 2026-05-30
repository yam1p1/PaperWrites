package io.github.yam1p1.paperWrites

import io.github.yam1p1.paperWrites.PaperWrites.Companion.plugin
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class MainLogic {

    val key = NamespacedKey(plugin, "paper_contents")

    fun givePaper(player: Player, title: String, contents: String){
        player.inventory.addItem(
            ItemStack(Material.PAPER).apply{
                itemMeta = itemMeta?.apply{
                    displayName(Component.text(title))
                    setEnchantmentGlintOverride(true)

                    persistentDataContainer.set(key, PersistentDataType.STRING,contents)
                }
            }
        )
    }

    fun init(){
        plugin.on<PlayerInteractEvent>{

            if (it.hand != EquipmentSlot.HAND) return@on

            if (it.action != Action.RIGHT_CLICK_AIR && it.action != Action.RIGHT_CLICK_BLOCK) return@on

            val item = it.item ?: return@on
            val meta = item.itemMeta ?: return@on

            val pdc = meta.persistentDataContainer

            if (pdc.has(key, PersistentDataType.STRING)) {
                val itemId = pdc.get(key, PersistentDataType.STRING)

                it.player.sendMessage(itemId as String)
                it.player.playSound(it.player,Sound.UI_BUTTON_CLICK, 1.0F, 1.0F)
            }
        }
    }
}