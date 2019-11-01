package ai.widya.deafble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.BeaconViewHolder> {

    private Context context;
    private List<IBeacon> beacons;

    public BeaconAdapter(Context context, List<IBeacon> beacons) {
        this.context = context;
        this.beacons = beacons;
    }

    @NonNull
    @Override
    public BeaconViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.beacon_item, viewGroup, false);
        return new BeaconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeaconViewHolder holder, int position) {
//        Beacon beacon = beacons.get(position);
//        holder.uuid.setText(String.valueOf(beacon.getId1()));
//        holder.major.setText(String.valueOf("major: "+beacon.getId2()));
//        holder.minor.setText(String.valueOf("minor: "+beacon.getId3()));
//        holder.distance.setText(String.valueOf("distance: "+beacon.getDistance()+" m"));
//        holder.mac.setText(beacon.getBluetoothAddress());
//        holder.rssi.setText("RSSI: " + beacon.getRssi());

        IBeacon beacon = beacons.get(position);
        holder.uuid.setText(String.valueOf(beacon.getUuid()));
        holder.major.setText(String.format("major: %s", beacon.getMajor()));
        holder.minor.setText(String.format("minor: %s", beacon.getMinor()));
        holder.distance.setText( "distance: " + beacon.getDistance() +" m");
        holder.mac.setText(beacon.getMac());
        holder.rssi.setText(String.format("RSSI: %d", beacon.getRssi()));
        holder.tx.setText(String.format("tx power: %d", beacon.getTxPower()));

    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }

    public class BeaconViewHolder extends RecyclerView.ViewHolder{

        private TextView uuid,major,minor,distance,rssi,mac,tx;

        public BeaconViewHolder(@NonNull View itemView) {
            super(itemView);

            uuid = itemView.findViewById(R.id.uuid);
            distance = itemView.findViewById(R.id.distance);
            major = itemView.findViewById(R.id.major);
            minor = itemView.findViewById(R.id.minor);
            rssi = itemView.findViewById(R.id.rssi);
            mac = itemView.findViewById(R.id.mac);
            tx = itemView.findViewById(R.id.tx);
        }
    }
}
