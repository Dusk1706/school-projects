import 'package:flutter/material.dart';
import 'package:flutter_application_3/tpc_conection.dart';

class AirConditionerScreen extends StatefulWidget {
  final Device device;

  const AirConditionerScreen({super.key, required this.device});

  @override
  // ignore: library_private_types_in_public_api
  _AirConditionerScreenState createState() => _AirConditionerScreenState();
}

class _AirConditionerScreenState extends State<AirConditionerScreen> {
    final SingletonTcpConnection _singletonTcpConnection =
      SingletonTcpConnection();

  int temperature = 18;
  bool isOn = false;
  int speed = 0;

  @override
  void initState() {
    super.initState();
  }

 void _sendCommand(String command, String value) {
    final tcpConnection = _singletonTcpConnection.tcpConnection;
    try {
      final devicePort = widget.device.port;
      final message = '$devicePort,$command$value';
      tcpConnection.sendCommand(message);
      
    // ignore: empty_catches
    } catch (e) {
      
    }
  }

  void togglePower() {
    setState(() {
      isOn = !isOn;
      _sendCommand('active=', isOn ? 'true' : 'false');
    });
  }

  void increaseTemperature() {
    setState(() {
      if (temperature < 29) {
        temperature++;
        _sendCommand('temperature=', temperature.toString());
      }
    });
  }

  void decreaseTemperature() {
    setState(() {
      if (temperature > 16) {
        temperature--;
        _sendCommand('temperature=', temperature.toString());
      }
    });
  }

  void changeSpeed() {
    setState(() {
      if (speed < 3) {
        speed++;
      } else {
        speed = 1;
      }
      _sendCommand('speed=', speed.toString());
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          'Control Remoto',
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.black,
          ),
        ),
        centerTitle: true,
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: Colors.grey[200],
                  borderRadius: BorderRadius.circular(10),
                ),
                child: Column(
                  children: [
                    Text(
                      '$temperature°C',
                      style: const TextStyle(
                          fontSize: 48, fontWeight: FontWeight.bold),
                    ),
                    const SizedBox(height: 10),
                    const Row(
                      mainAxisAlignment: MainAxisAlignment.spaceAround,
                      children: [
                        Column(
                          children: [
                            Icon(Icons.thermostat, size: 30),
                            Text('Temperatura'),
                          ],
                        ),
                        Column(
                          children: [
                            Icon(Icons.waves, size: 30),
                            Text('Velocidad'),
                          ],
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              const SizedBox(height: 20),
              GridView.count(
                crossAxisCount: 3,
                shrinkWrap: true,
                mainAxisSpacing: 10,
                crossAxisSpacing: 10,
                children: [
                  IconButton(
                    onPressed: togglePower,
                    icon: const Icon(Icons.power_settings_new),
                    iconSize: 50,
                  ),
                  IconButton(
                    onPressed: decreaseTemperature,
                    icon: const Icon(Icons.arrow_downward),
                    iconSize: 50,
                  ),
                  IconButton(
                    onPressed: () => print('btn2'),
                    icon: const Icon(Icons.ac_unit),
                    iconSize: 50,
                  ),
                  IconButton(
                    onPressed: () => print('btn3'),
                    icon: const Icon(Icons.sync),
                    iconSize: 50,
                  ),
                  IconButton(
                    onPressed: increaseTemperature,
                    icon: const Icon(Icons.arrow_upward),
                    iconSize: 50,
                  ),
                  IconButton(
                    onPressed: changeSpeed,
                    icon: const Icon(Icons.toys),
                    iconSize: 50,
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
