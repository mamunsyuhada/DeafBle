package ai.widya.deafble;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity   implements BeaconConsumer {

    protected static final String TAG = "MainActivity";
    private BeaconManager beaconManager;

    private BluetoothAdapter bluetoothAdapter;
    private int REQUEST_ENABLE_BT = 1;
    private int REQUEST_ENABLE_LOCATION = 1;

    private ArrayList<IBeacon> beaconDevice = new ArrayList<>();
    private RecyclerView rv;
    private BeaconAdapter adapter;
    private IBeacon iBeacon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE Not Supported", Toast.LENGTH_SHORT).show();
        }

        //bluetooth manager
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();

        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                MainActivity.this, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{ACCESS_FINE_LOCATION}, REQUEST_ENABLE_LOCATION);
            return;
        }

        final LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE );

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            enableGPS();

        //beacon library
        beaconManager = BeaconManager.getInstanceForApplication(this);
        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        //set khusus ibeacon
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));
        beaconManager.bind(this);

        //ui
        rv = findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void enableGPS() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("GPS is disabled in your device. Would you like to enable it?")
                .setCancelable(false)
                .setPositiveButton("Enable",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                Intent callGPSSettingIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(callGPSSettingIntent);
                            }
                        });
        alertDialogBuilder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.removeAllRangeNotifiers();
        beaconManager.addRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
//                beaconDevice.clear();
                if (beacons.size() > 0) {
//                    beaconDevice.clear();
//                    Toast.makeText(MainActivity.this, "beacon : "+beacons.size(), Toast.LENGTH_SHORT).show();



//                    for(Iterator<Beacon> iterator = beacons.iterator(); iterator.hasNext();) {
//                        Beacon beacon = iterator.next();
//                        if(!beaconDevice.contains(beacon)) {
////                            Toast.makeText(MainActivity.this, beacon.getId3().toString(), Toast.LENGTH_SHORT).show();
//
//                            iBeacon = new IBeacon(String.valueOf(beacon.getId1()),String.valueOf(beacon.getId2()), String.valueOf(beacon.getId3()), beacon.getDistance(), beacon.getRssi(), beacon.getBluetoothAddress(), beacon.getTxPower());
//                            beaconDevice.add(iBeacon);
//                        }else{
//
//                        }
//                    }
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapter = new BeaconAdapter(getApplicationContext(),beaconDevice);
//                            adapter.notifyDataSetChanged();
//                            rv.setAdapter(adapter);
//                        }
//                    });


                    for (Beacon beacon : beacons) {
//                    for (int b=0; b<beacons.size();b++){
//
                        iBeacon = new IBeacon(String.valueOf(beacon.getId1()),String.valueOf(beacon.getId2()), String.valueOf(beacon.getId3()), beacon.getDistance(), beacon.getRssi(), beacon.getBluetoothAddress(), beacon.getTxPower());
//                        iBeacon = new IBeacon(String.valueOf(beacons.getId1()),String.valueOf(beacon.getId2()), String.valueOf(beacon.getId3()), beacon.getDistance(), beacon.getRssi(), beacon.getBluetoothAddress(), beacon.getTxPower());
////                        int index = beaconDevice.indexOf(iBeacon);
////                        if (index==-1){
//
////                        }else {
////                            beaconDevice.set(index, iBeacon);
////                        }
//
////                        if (iBeacon.getUuid().equals())
//
////                        beaconDevice.add(iBeacon);
////
////                        ListIterator<IBeacon> iterator = beaconDevice.listIterator();
////                        while (iterator.hasNext()){
////                            IBeacon beaconTest = iterator.next();
////                            if (beaconTest.getUuid().equals(iBeacon.getMinor())){
////                                beaconTest.setRssi(iBeacon.getRssi());
////                                beaconTest.setDistance(iBeacon.getDistance());
////                            }else {
////                                beaconDevice.add(iBeacon);
////                            }
////                            iterator.remove();
////                            iterator.add(beaconTest);
////                        }
//
//
//
//
//
//
                        if (beaconDevice.size()==0){

                            beaconDevice.add(iBeacon);
                        }else {
                            for (int i = 0; i<beaconDevice.size();i++){
//
                                String check3 = beaconDevice.get(i).getMinor();
                                String check2 = beaconDevice.get(i).getMajor();
                                String check1 = beaconDevice.get(i).getUuid();

                                String result1 = beacon.getId1().toString();
                                String result2 = beacon.getId2().toString();
                                String result3 = beacon.getId3().toString();

                                if (check1.equals(result1) && check2.equals(result2) && check3.equals(result3)){
                                    beaconDevice.set(i, iBeacon);
                                    Toast.makeText(MainActivity.this, "index "+i+" minor "+iBeacon.getMinor(), Toast.LENGTH_SHORT).show();
                                }else {
                                    beaconDevice.add(iBeacon);
//
                                }
                            }
                        }
                    }
                    adapter = new BeaconAdapter(getApplicationContext(),beaconDevice);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);
                }
                else {
//                    Toast.makeText(MainActivity.this, "no beacon", Toast.LENGTH_LONG).show();

                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
            Toast.makeText(MainActivity.this, "error: "+e, Toast.LENGTH_LONG).show();

        }
    }
}
