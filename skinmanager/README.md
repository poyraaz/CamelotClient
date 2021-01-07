- CTRL + SHIFT + T tuşlarına basıyoruz ve arama kısmına **SkinManager** yazıyoruz.
- "loadProfileTextures" methodunu buluyoruz ve "Minecraft.getMinecraft().addScheduledTask(new Runnable()" üstüne;
`ClientSkinManager.instance.getSkin(profile, map, skinAvailableCallback);`
yazıyoruz kaydedip oyunu açıyoruz.
