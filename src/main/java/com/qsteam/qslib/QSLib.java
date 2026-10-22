package com.qsteam.qslib;

import com.qsteam.qslib.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = "@{MOD_ID}",
    name = "@{MOD_NAME}",
    version = "@{MOD_VERSION}",
    acceptableRemoteVersions = "*",
    customProperties = {
        @Mod.CustomProperty(k = "license", v = "MPL-2.0")
    }
)
public final class QSLib {

    @SidedProxy(
        clientSide = "@{ROOT_PACKAGE}.@{MOD_ID}.proxy.ClientProxy",
        serverSide = "@{ROOT_PACKAGE}.@{MOD_ID}.proxy.CommonProxy"
    )
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
