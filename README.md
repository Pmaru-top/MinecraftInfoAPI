# MinecraftInfoAPI
A convenient api to get Minecraft uuid and skin

If you cannot read Chinese, please use Google Translate
==

`MinecraftInfoAPI` 是一个可以让你轻松获取`Minecraft` 玩家信息的库

没什么技术力,只是方便点

目前,`MinecraftInfoAPI` 有以下功能:
- 通过`用户名` 获取`UUID` 或通过`UUID` 反查`用户名`
- 通过`UUID` 获取`皮肤` 和`披风` 
- 通过`UUID` 获得 `名称历史` 以及名称的修改时间
- 获取`Minecraft` 相关网站的状态(例如`Minecraft.net` `mojang.com`)

本项目语法和编译版本为`Java8`

用到的库 : [`Fastjson`](https://github.com/alibaba/fastjson)

到[Releases](https://github.com/MX233/MinecraftInfoAPI/releases) 下载并导入到你的项目中即可使用

我是一个新手,可能做的不好,你可以clone下来自行修改

用法:

- 用户名查询UUID
        
      Player player = Player.getPlayer("CuteStarX");
      System.out.println(player.getName() + " 的UUID是: " + player.getUuid());

- 查询10个以内的玩家UUID

      List<String> players = new ArrayList<>();
      players.add("CuteStarX");
      
      Map<String,String> map = new MultiplePlayers().getPlayers(players);
      map.keySet().forEach(uuid -> System.out.println(map.get(uuid) + " 的UUID是: " + uuid));
      
- 查询玩家曾用名
        
      NameHistory nameHistory = NameHistory.getNameHistory("e3beb716afa1451b96c6ddfebd1ce1fb");
      System.out.println("这个UUID现在的玩家名称为: " + nameHistory.getCurrentName());
      System.out.println("这个UUID初始玩家名称为: " + nameHistory.getInitName());
        
      System.out.println("这个UUID使用过的玩家名称:");
      nameHistory.getNameHistoryData().forEach(data -> System.out.println("名称: " + data.getName() + " 修改时间: " + Util.toTime(data.getChangedToAt())));

- 查询玩家皮肤

      Profile profile = Profile.getProfile(Player.getPlayer("CuteStarX").getUuid());
      System.out.println("皮肤模型是: " + profile.getModel());
      System.out.println("皮肤链接是: " + profile.getSkinUrl());
      System.out.println("披风链接是: " + profile.getCapeUrl());
      
      //将皮肤保存到本地
              try (OutputStream out = new FileOutputStream("skin.png")) {
                  out.write(profile.getSkinBytes());
              }