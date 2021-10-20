package org.serverct.pay

import org.serverct.pay.utils.Service
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.SecuredFile

object PayApi : Plugin() {

    @Config(value = "key.yml")
    lateinit var key: SecuredFile
        private set

    override fun onEnable() {
        Service.load()
    }

}