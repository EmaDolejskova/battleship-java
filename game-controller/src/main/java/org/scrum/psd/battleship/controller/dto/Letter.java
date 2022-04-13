package org.scrum.psd.battleship.controller.dto;

public enum Letter {
    A, B, C, D, E, F, G, H;
    
    public static Letter letterFromNumber(int number) {
        switch (number) {
            case 0:
                return Letter.A;
            case 1:
                return Letter.B;
            case 2:
                return Letter.C;
            case 3:
                return Letter.D;
            case 4:
                return Letter.E;
            case 5:
                return Letter.F;
            case 6:
                return Letter.G;
            case 7:
                return Letter.H;
            default:
                return Letter.A;
        }
    }
}

