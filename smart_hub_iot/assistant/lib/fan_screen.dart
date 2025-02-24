import 'package:flutter/material.dart';
import 'package:flutter_application_3/tpc_conection.dart';

class FanScreen extends StatefulWidget {
  final Device device;
  const FanScreen({super.key, required this.device});

  @override
  _FanScreenState createState() => _FanScreenState();
}

class _FanScreenState extends State<FanScreen> {
  final SingletonTcpConnection _singletonTcpConnection =
      SingletonTcpConnection();

  bool isOn = false;
  double fanSpeed = 0.0;

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
      
    } catch (e) {
      
    }
  }


  void togglePower() {
    setState(() {
      isOn = !isOn;
      _sendCommand('state=', isOn ? 'true' : 'false');
    });
  }

  void changeSpeed(double valor) {
    setState(() {
      fanSpeed = valor;
      _sendCommand('intensity=', (fanSpeed * 10).toString());
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          'Abanico Control',
          style: TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.blue,
          ),
        ),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.toys,
                size: 100, color: isOn ? Colors.blue : Colors.grey),
            const SizedBox(height: 20),
            Text(
              isOn ? 'Abanico encendido' : 'Abanico apagado',
              style: const TextStyle(fontSize: 24),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: () {
                    setState(() {
                      togglePower();
                    });
                  },
                  child: Text(isOn ? 'Apagar' : 'Encender'),
                ),
                Slider(
                  value: fanSpeed,
                  min: 0.0,
                  max: 10.0,
                  divisions: 10,
                  onChanged: (newValue) {
                    if (isOn) {
                      setState(() {
                        fanSpeed = newValue;
                        changeSpeed(fanSpeed);
                      });
                    }
                  },
                ),
              ],
            ),
            Text(
              'Velocidad: ${fanSpeed.toStringAsFixed(1)}',
              style: const TextStyle(fontSize: 18),
            ),
          ],
        ),
      ),
    );
  }
}
