package sourcecoded.creativeA.client.camera;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;

public class TweenPosition {

    double x;
    double y;
    double z;

    double camH;
    double camV;

    double rotH;
    double rotV;

    public long timeOffset;

    public TweenPosition(double x, double y, double z, double camH, double camV, double rotH, double rotV) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.camH = camH;
        this.camV = camV;

        this.rotH = rotH;
        this.rotV = rotV;
    }

    public static TweenPosition create() {
        EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();
        return new TweenPosition(player.posX, player.posY, player.posZ, player.cameraYaw, player.cameraPitch, player.rotationYaw, player.rotationPitch);
    }

    public static TweenPosition interpolate(TweenPosition base, TweenPosition target, float progress) {
        double x = base.getX() + ((target.getX() - base.getX()) * progress);
        double y = base.getY() + ((target.getY() - base.getY()) * progress);
        double z = base.getZ() + ((target.getZ() - base.getZ()) * progress);

        double camH = base.getCamH() + ((target.getCamH() - base.getCamH()) * progress);
        double camV = base.getCamV() + ((target.getCamV() - base.getCamV()) * progress);

        double rotH = base.getRotH() + ((target.getRotH() - base.getRotH()) * progress);
        double rotV = base.getRotV() + ((target.getRotV() - base.getRotV()) * progress);

        return new TweenPosition(x, y, z, camH, camV, rotH, rotV);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getCamH() {
        return camH;
    }

    public double getCamV() {
        return camV;
    }

    public double getRotV() {
        return rotV;
    }

    public double getRotH() {
        return rotH;
    }

    public JsonObject toJSON(int id) {
        JsonObject object = new JsonObject();
        object.add("id", new JsonPrimitive(id));
        object.add("x", new JsonPrimitive(x));
        object.add("y", new JsonPrimitive(y));
        object.add("z", new JsonPrimitive(z));
        object.add("camH", new JsonPrimitive(camH));
        object.add("camV", new JsonPrimitive(camV));
        object.add("rotH", new JsonPrimitive(rotH));
        object.add("rotV", new JsonPrimitive(rotV));
        return object;
    }

    public static TweenPosition fromJSON(JsonObject o) {
        return new TweenPosition(g(o, "x"), g(o, "y"), g(o, "z"), g(o, "camH"), g(o, "camV"), g(o, "rotH"), g(o, "rotV"));
    }

    static double g(JsonObject object, String member) {
        return object.get(member).getAsDouble();
    }
}
