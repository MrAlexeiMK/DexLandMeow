package dexland.argento;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.*;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventListener {
    long time1 = 0;
    long time2 = 0;
    long start_time = 0;
    public static int cooldown = 500;
    public static String killmsg = "%player%, ты няшка <3";
    public static String bedmsg = "Ммм.. какая вкусная кроватка... <3";
    public static String winmsg = "gg";
    public static String deathmsg = "Эхх :(";
    public static String joinmsg = "Всем удачной игры :3";
    public static boolean timer = true;

    public void send(String str) {
        if(Minecraft.getMinecraft().thePlayer != null) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
        }
    }

    @SubscribeEvent
    public void join(EntityJoinWorldEvent e) {
        if(Minecraft.getMinecraft().thePlayer != null) {
            if (e.entity instanceof EntityPlayer) {
                if (Minecraft.getMinecraft().thePlayer.getGameProfile().getName().equals(((EntityPlayer) e.entity).getGameProfile().getName())) {
                    long time2 = System.currentTimeMillis();
                    if(time2 - time1 > 1000) {
                        send("Вы играете с модом DexLandMeow (автор: MrAlexeiMK)");
                        time1 = time2;
                    }
                }
            }
        }
    }

    public void sendInChat(String msg) {
        if(Minecraft.getMinecraft().thePlayer != null) {
            long time3 = System.currentTimeMillis();
            if (time3 - time2 > cooldown) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("!" + msg);
                time2 = time3;
            }
        }
    }

    public static void sendCDMessage(final String msg, final int cd) {
        Thread th = new Thread() {
            public void run() {
                try {
                    sleep(cd);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(Minecraft.getMinecraft().thePlayer != null) {
                    Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
                }
            }
        };
        th.start();
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        String msg = e.message.getUnformattedText();
        String p_name = Minecraft.getMinecraft().thePlayer.getGameProfile().getName();
        if(msg.contains(p_name) && msg.contains(".helpmeow")) {
            send("Если не ставятся сообщения - ставьте в одиночном режиме");
            send(".setkillmsg [сообщение/null] - установить сообщение при убийстве");
            send(".setbedmsg [сообщение/null] - установить сообщение при ломании кровати");
            send(".setjoinmsg [сообщение/null] - установить сообщение при входе в игру");
            send(".setwinmsg [сообщение/null] - установить сообщение при победе");
            send(".setdeathmsg [сообщение/null] - установить сообщение при вашей смерти");
            send(".timer [on/off] - включить/выключить таймер, измеряющий время катки");
            send(".setcooldown [число] - установить задержку между вашими сообщениями (в миллисекундах)");
            send("В .setkillmsg можно добавить %player%, для обращению к игроку, которого вы убили");
            send("Чтобы выключить отдельную фукнцию, поставьте в сообщение null");
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setcooldown")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setcooldown")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                try {
                    int num = Integer.parseInt(m.replaceAll(" ", ""));
                    cooldown = num;
                    send("Установлена задержка: " + String.valueOf(num));
                } catch(Exception ee) {
                    send(".setcooldown [число] - установить задержку между вашими сообщениями (в миллисекундах)");
                };
            }
            else {
                send(".setcooldown [число] - установить задержку между вашими сообщениями (в миллисекундах)");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".timer on")) {
            timer = true;
            send("Таймер игры включён");
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".timer off")) {
            timer = false;
            send("Таймер игры выключен");
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setkillmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setkillmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals(" ")) {
                killmsg = m;
                send("Установлено сообщение: " + m);
            }
            else {
                send(".setkillmsg [сообщение/null] - установить сообщение при убийстве");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setbedmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setbedmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                bedmsg = m;
                send("Установлено сообщение: " + m);
            }
            else {
                send(".setbedmsg [сообщение/null] - установить сообщение при ломании кровати");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setwinmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setwinmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                winmsg = m;
                send("Установлено сообщение: " + m);
            }
            else {
                send(".setwinmsg [сообщение/null] - установить сообщение при победе");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setdeathmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setdeathmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals("")) {
                deathmsg = m;
                send("Установлено сообщение: " + m);
            }
            else {
                send(".setdeathmsg [сообщение/null] - установить сообщение при вашей смерти");
            }
            e.setCanceled(true);
        }
        else if(msg.contains(p_name) && msg.contains(".setjoinmsg")) {
            String m = "";
            String[] spl = msg.split(" ");
            int i = 0;
            while(!spl[i].equals(".setjoinmsg")) {
                ++i;
                if(i == spl.length) break;
            }
            if(i != spl.length) {
                for(int j = i+1; j < spl.length; ++j) {
                    m += spl[j] + " ";
                }
            }
            if(!m.equals(" ")) {
                joinmsg = m;
                send("Установлено сообщение: " + m);
            }
            else {
                send(".setjoinmsg [сообщение/null] - установить сообщение при входу в игру");
            }
            e.setCanceled(true);
        }
        else if(msg.split(" ").length > 6 && msg.split(" ")[3].contains("был") && msg.split(" ")[4].contains("убит") && msg.split(" ")[5].contains("игроком")) {
            String killer = msg.split(" ")[6].substring(0, msg.split(" ")[6].length()-1);
            String killed = msg.split(" ")[2];
            String m = killmsg.replaceAll("%player%", killed);
            if(killer.equals(p_name) && !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.split(" ").length > 8 && msg.split(" ")[3].contains("был") && msg.split(" ")[4].contains("скинут") && msg.split(" ")[5].contains("в") && msg.split(" ")[6].contains("бездну") && msg.split(" ")[7].contains("игроком")) {
            String killer = msg.split(" ")[8].substring(0, msg.split(" ")[8].length()-1);
            String killed = msg.split(" ")[2];
            String m = killmsg.replaceAll("%player%", killed);
            if(killer.equals(p_name) && !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.split(" ").length > 6 && msg.split(" ")[3].contains("разрушил") && msg.split(" ")[4].contains("кровать") && msg.split(" ")[5].contains("команды")) {
            String who = msg.split(" ")[2];
            String m = bedmsg;
            if(who.equals(p_name) &&  !m.contains("null")) {
                sendInChat(m);
            }
        }
        else if(msg.contains("Победит только одна, сильнейшая команда!")) {
            if(timer) {
                start_time = (long)(System.currentTimeMillis()/1000);
                sendCDMessage("Отчёт времени игры начался!", 500);
            }
            if(!joinmsg.contains("null")) {
                sendInChat(joinmsg);
            }
        }
        else if(msg.contains("Перезагрузка сервера через 10 секунд!")) {
            if(!winmsg.contains("null")) {
                sendInChat(winmsg);
            }
            if(timer) {
                long seconds = (long)(System.currentTimeMillis()/1000) - start_time;
                long minutes = seconds/60;
                seconds = seconds%60;
                long hours = minutes/60;
                minutes = minutes%60;
                String res = String.valueOf(hours)+":"+String.valueOf(minutes)+":"+String.valueOf(seconds);
                send("Время игры: "+res);
            }
        }
        else if(msg.contains(p_name+ " упал в бездну") || msg.contains(p_name+ " был убит игроком") || msg.contains(p_name + " был скинут в бездну игроком")) {
            if(!deathmsg.contains("null")) {
                sendInChat(deathmsg);
            }
        }
    }
}
