package org.teamvoided.krabnet

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.krabnet.init.KNParticleTypes

@Suppress("unused")
object KrabNet {
    const val MODID = "krabnet"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(KrabNet::class.simpleName)

    fun init() {
        log.info("Hello from Common")
        KNParticleTypes.init()
    }

    fun id(path: String) = Identifier.of(MODID, path)
}