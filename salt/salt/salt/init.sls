hourly_highstate:
    schedule.present:
        - function: state.highstate
        - hours: 1
