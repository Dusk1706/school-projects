import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_3/fan_screen.dart';
import 'package:flutter_application_3/air_conditioner_screen.dart';
import 'package:flutter_application_3/light_screen.dart';
import 'package:flutter_application_3/plug_screen.dart';
import 'package:flutter_application_3/tpc_conection.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Smart Hub',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Smart Hub'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Device> devices = [];

  final SingletonTcpConnection _singletonTcpConnection =
      SingletonTcpConnection();

  @override
  void initState() {
    super.initState();
    _connectToServer();
  }

  void _connectToServer() async {
    try {
      final tcpConnection = _singletonTcpConnection.tcpConnection;
      await tcpConnection.connect(
        () {
          setState(() {});
        },
      );
      tcpConnection.sendCommand('hub,SmartHub');
    } catch (e) {
      if (kDebugMode) {
        print("Received error while connecting to server: $e");
      }
    } finally {
      setState(() {});
    }
  }

  void _sendCommand(String command) {
    try {
      final tcpConnection = _singletonTcpConnection.tcpConnection;
      tcpConnection.sendCommand(command);
      ScaffoldMessenger.of(context)
          .showSnackBar(SnackBar(content: Text('Comando enviado: $command')));
    } catch (e) {
      ScaffoldMessenger.of(context)
          .showSnackBar(SnackBar(content: Text('Error: $e')));
    }
  }

  Widget _renderLoadingScreen() {
    return const Center(
      child: CircularProgressIndicator(),
    );
  }

  Widget _renderErrorScreen(String message) {
    return Center(
      child: Text(
        message,
        style: const TextStyle(fontSize: 16, color: Colors.red),
      ),
    );
  }

  Widget _getDeviceScreen(Device device) {
    switch (device.type) {
      case DeviceType.light:
        return LightScreen(device: device);
      case DeviceType.airConditioner:
        return AirConditionerScreen(device: device);
      case DeviceType.fan:
        return FanScreen(device: device);
      case DeviceType.plug:
        return PlugScreen(device: device);
    }
  }

  Widget _renderData() {    
    final devices = _singletonTcpConnection.tcpConnection.devices;
    if (devices.isEmpty) {
      return const Center(child: Text('No se encontraron dispositivos'));
    }

    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 8.0),
      child: ListView.builder(
        itemCount: devices.length,
        itemBuilder: (context, index) {
          final device = devices[index];
          return OutlinedButton(
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (context) => _getDeviceScreen(device),
                ),
              );
            },
            child: Text(device.name),
          );
        },
      ),
    );
  }

  Widget _renderBody() {
    final tcpConnection = _singletonTcpConnection.tcpConnection;

    final state = tcpConnection.state;

    switch (state) {
      case TcpConnectionState.connecting:
        return _renderLoadingScreen();
      case TcpConnectionState.connected:
        return _renderData();
      case TcpConnectionState.disconnected:
        return _renderErrorScreen('No se pudo conectar al servidor');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: Text(
          widget.title,
          style: const TextStyle(
            fontSize: 24,
            fontWeight: FontWeight.bold,
            color: Colors.blue,
          ),
        ),
        centerTitle: true,
      ),
      body: _renderBody(),
    );
  }
}
