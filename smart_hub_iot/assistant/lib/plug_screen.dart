import 'package:flutter/material.dart';
import 'package:flutter_application_3/tpc_conection.dart';

class PlugScreen extends StatefulWidget {
  final Device device;
  const PlugScreen({super.key, required this.device});

  @override
    // ignore: library_private_types_in_public_api
  _PlugScreenState createState() => _PlugScreenState();
}

class _PlugScreenState extends State<PlugScreen> {
  bool isOn = false;
  final SingletonTcpConnection _singletonTcpConnection =
      SingletonTcpConnection();

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
      _sendCommand('active=', isOn ? 'true' : 'false');
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          'Enchufe Control',
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
            Icon(Icons.power,
                size: 100, color: isOn ? Colors.green : Colors.grey),
            const SizedBox(height: 20),
            const Text('Controla tu enchufe aquí',
                style: TextStyle(fontSize: 24)),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                togglePower();
              },
              style: ElevatedButton.styleFrom(
                padding:
                    const EdgeInsets.symmetric(horizontal: 40, vertical: 20),
                textStyle: const TextStyle(fontSize: 20),
              ),
              child: Text(isOn ? 'Apagar' : 'Encender'),
            ),
          ],
        ),
      ),
    );
  }
}
