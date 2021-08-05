package tax.cute.minecraftinfoapi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args)throws IOException {
        //No data will return a null pointer
        //It is recommended to pay attention in actual use
        getUUid("CuteStarX");
        System.out.println();
        getSkin("e3beb716-afa1-451b-96c6-ddfebd1ce1fb");
        System.out.println();
        getNameHistory("e3beb716-afa1-451b-96c6-ddfebd1ce1fb");
    }

    static void getUUid(String userName) throws IOException{
        UUID uuid = UUID.getId(userName);
        System.out.println(
                "uuid of " + uuid.getName() + " is:" + uuid.getId()
        );
    }

    static void getStatus() throws IOException{
        Status status = Status.getStatus();
        System.out.println("Mojang.com status is:" + status.getMojang_com());
        System.out.println("Minecraft.com status is:" + status.getMinecraft_net());
        //...
    }

    static void getSkin(String uuid) throws IOException{
        Skin skin = Skin.getSkin(uuid);
            System.out.println("The link to this skin is:" + skin.getSkinUrl());
//        System.out.println("The link to this skin is:" + skin.getCapeUrl());
        boolean isDownloadSkin = false;
        boolean isDownloadCape = false;

        if (isDownloadSkin) {
            OutputStream out = new FileOutputStream("\\Skin.PNG");
            out.write(skin.getSkinBytes());
            System.out.println("Skin download is complete");
            out.flush();
            out.close();
        }

//        if (isDownloadCape) {
//            OutputStream out = new FileOutputStream("\\Cape.PNG");
//            out.write(skin.getCapeBytes());
//            System.out.println("Cape download is complete");
//            out.flush();
//            out.close();
//        }
    }

    static void getNameHistory(String uuid){
        NameHistory nameHistory = NameHistory.getNameHistory(uuid);
        if (nameHistory.getData() == null) {
            System.out.println("no data");
            return;
        }
        for (int i = 0; i < nameHistory.getData().size(); i++) {
            System.out.println(nameHistory.getData().get(i).getName());
            if (nameHistory.getData().get(i).getTimestamp() != -1) {
                System.out.println(nameHistory.getData().get(i).getTime());
            } else {
                System.out.println("init username");
            }
            System.out.println();
        }
    }

}