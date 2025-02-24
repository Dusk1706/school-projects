import 'package:flutter/material.dart';
import 'package:flutter_application_3/tpc_conection.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';

Color pickerColor = Colors.yellow;
Color currentColor = Colors.yellow;

class LightScreen extends StatefulWidget {
  final Device device;
  const LightScreen({super.key, required this.device});

  @override
  // ignore: library_private_types_in_public_api
  _LightScreenState createState() => _LightScreenState();
}

class _LightScreenState extends State<LightScreen> {
  bool isOn = false;
  double brightness = 0.5;
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

  void setBrightness(double value) {
    setState(() {
      brightness = value;
      _sendCommand('intensity=', (brightness * 100).toString());
    });
  }

  void changeColor(Color color) {
    setState(() {
      pickerColor = color;
    });
  }

  void applyColor() {
    setState(() {
      currentColor = pickerColor;
      _sendCommand('color=', pickerColor.value.toRadixString(16));
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: const Text(
          'Foco Control',
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
          children: <Widget>[
            Icon(
              isOn ? Icons.lightbulb : Icons.lightbulb_outline,
              size: 100,
              color: isOn ? currentColor.withOpacity(brightness) : Colors.grey,
            ),
            const SizedBox(height: 20),
            const Text(
              'Controla tu foco aquí',
              style: TextStyle(fontSize: 24),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: togglePower,
              child: Text(isOn ? 'Apagar' : 'Encender'),
            ),
            const SizedBox(height: 20),
            const Text('Brillo'),
            Slider(
              value: brightness,
              min: 0,
              max: 1,
              divisions: 10,
              label: (brightness * 100).toInt().toString(),
              onChanged: isOn ? setBrightness : null,
            ),
            const SizedBox(height: 20),
            const Text('Color'),
            ElevatedButton(
              onPressed: isOn
                  ? () {
                      showDialog(
                        context: context,
                        builder: (context) {
                          return AlertDialog(
                            title: const Text('Selecciona un color'),
                            content: SingleChildScrollView(
                              child: ColorPicker(
                                pickerColor: pickerColor,
                                onColorChanged: changeColor,
                                pickerAreaHeightPercent: 0.8,
                              ),
                            ),
                            actions: <Widget>[
                              TextButton(
                                onPressed: () {
                                  applyColor();
                                  Navigator.of(context).pop();
                                },
                                child: const Text('Aceptar'),
                              ),
                            ],
                          );
                        },
                      );
                    }
                  : null,
              child: const Text('Seleccionar color'),
            ),
            const SizedBox(height: 20),
          ],
        ),
      ),
    );
  }
}
