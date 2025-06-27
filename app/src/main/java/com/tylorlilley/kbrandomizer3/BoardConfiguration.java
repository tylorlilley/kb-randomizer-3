package com.tylorlilley.kbrandomizer3;

import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by Tylor on 6/4/2016.
 */


public class BoardConfiguration extends ContextWrapper {

    int CONFIG_SIZE = 69;
    int[] config;

    // Constructors
    public BoardConfiguration(Context base, String rawInput) {
        super(base);
        initialConfiguration(rawInput);
    }

    public BoardConfiguration(Context base, int[] rawInput) {
        super(base);
        initialConfiguration(rawInput);
    }

    public void initialConfiguration(String rawInput) {
        config = new int[CONFIG_SIZE];
        if (rawInput.length() < CONFIG_SIZE) {
            for (int i = 0; i < CONFIG_SIZE; i++) {
                config[i] = 0;
            }
        } else {
            for (int i = 0; i < CONFIG_SIZE; i++) {
                config[i] = Character.getNumericValue(rawInput.charAt(i));
            }
        }
    }

    public void initialConfiguration(int[] input) {
        config = input;
    }

    @Override
    public String toString() {
        String configAsString = "";
        for (int i = 0; i < CONFIG_SIZE; i++) {
            configAsString = configAsString.concat(Integer.toString(config[i]));
            config[i] = Character.getNumericValue(configAsString.charAt(i));
        }
        return configAsString;
    }

    // Convert number to array position

    private int getCardPosition(int card) {
        return 37 + (card * 2);
    }

    private int getBoardPosition(int quadrant) {
        return 2 + (quadrant * 7);
    }

    private int getNomadPosition(int space) {
        return 53 + (space * 2);
    }

    // Convert number to identifying number
    private int getNomadSpace(int space) {
        return (10 * config[getNomadPosition(space)]) + config[getNomadPosition(space) + 1];
    }

    public int getCardSet(int card) {
        return config[getCardPosition(card)];
    }

    public int getCardName(int card) {
        return config[getCardPosition(card) + 1];
    }

    public int getQuadrantSet(int quadrant) {
        return config[getBoardPosition(quadrant)];
    }

    public int getQuadrantName(int quadrant) {
        return config[getBoardPosition(quadrant) + 1];
    }

    public int getQuadrantFlipped(int quadrant) {
        return config[getBoardPosition(quadrant) + 2];
    }

    public int getQuadrantCapitols(int quadrant) {
        return config[getBoardPosition(quadrant) + 3];
    }

    public int getQuadrantCaves(int quadrant) {
        return config[getBoardPosition(quadrant) + 4];
    }

    public int getQuadrantVolcano(int quadrant) {
        return config[getBoardPosition(quadrant) + 5];
    }

    public int getQuadrantFerry(int quadrant) {
        return config[getBoardPosition(quadrant) + 6];
    }

    public int getTotalCards() {
        return config[1];
    }

    public int getTotalBoards() {
        return config[0];
    }

    public int getQuadrantCastles(int quadrant) {
        if (getQuadrantSet(quadrant) == 0) {
            if (getQuadrantName(quadrant) < 2) return 2;
            else return 1;
        } else if (getQuadrantSet(quadrant) == 2 || getQuadrantSet(quadrant) == 5) return 1;
        else return 0;
    }

    public int getQuadrantNomads(int quadrant) {
        if (getQuadrantSet(quadrant) == 1) {
            if (getQuadrantName(quadrant) == 0) return 3;
            else return 1;
        } else return 0;
    }

    public int getQuadrantNomadSpace(int quadrant) {
        int usedNomads = 0;
        for (int i = 0; i < quadrant; i++) {
            usedNomads += getQuadrantNomads(i);
        }
        return usedNomads;
    }

    // Parse identifying number to string

    public String parseQuadrant(int quadrant) {
        String placement = parseBoard(getQuadrantSet(quadrant), getQuadrantName(quadrant));
        if (getQuadrantFlipped(quadrant) == 1) {
            placement = placement.concat(" ↷");
        }
        placement = placement.concat(parseQuadrantCapitols(quadrant));
        for (int i = 0; i < getQuadrantNomads(quadrant); i++) {
            placement = placement.concat(" - ");
            placement = placement.concat(parseNomadSpace(getQuadrantNomadSpace(quadrant) + i));
        }
        if (getQuadrantCaves(quadrant) == 1) {
            placement = placement.concat(" - " + getString(R.string.cave));
        }
        if (getQuadrantVolcano(quadrant) == 1) {
            placement = placement.concat(" - " + getString(R.string.volcano));
        }
        if (getQuadrantFerry(quadrant) == 1) {
            placement = placement.concat(" - " + getString(R.string.ferry));
        }
        return placement;
    }

    private String parseQuadrantCapitols(int quadrant) {
        switch (getQuadrantCapitols(quadrant)) {
            case 0:
                return "";
            case 1:
                return " - " + getString(R.string.capitol);
            case 2:
                return " - " + getString(R.string.capitol) + "(1)";
            case 3:
                return " - " + getString(R.string.capitol) + "(2)";
            case 4:
                return " - " + getString(R.string.capitol) + "(1 + 2)";
        }
        return "ERROR";
    }

    private String parseNomadSpace(int space) {
        switch (getNomadSpace(space)) {
            case 0:
                return getString(R.string.nomad_space_sword);
            case 1:
                return getString(R.string.nomad_space_relocation);
            case 2:
                return getString(R.string.nomad_space_treasure);
            case 3:
                return getString(R.string.nomad_space_outpost);
            case 4:
                return getString(R.string.nomad_space_grass);
            case 5:
                return getString(R.string.nomad_space_forest);
            case 6:
                return getString(R.string.nomad_space_flowers);
            case 7:
                return getString(R.string.nomad_space_desert);
            case 8:
                return getString(R.string.nomad_space_canyon);
            case 9:
                return getString(R.string.nomad_space_water);
            case 10:
                return getString(R.string.nomad_space_mountain);
            case 11:
                return getString(R.string.nomad_space_sword);
            case 12:
                return getString(R.string.nomad_space_relocation);
            case 13:
                return getString(R.string.nomad_space_treasure);
            case 14:
                return getString(R.string.nomad_space_outpost);
        }
        return "ERROR";
    }

    public String parseCard(int card) {
        switch (getCardSet(card)) {
            case 0: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_farmers);
                    case 1:
                        return getString(R.string.card_citizens);
                    case 2:
                        return getString(R.string.card_lords);
                    case 3:
                        return getString(R.string.card_hermits);
                    case 4:
                        return getString(R.string.card_knights);
                    case 5:
                        return getString(R.string.card_discoverers);
                    case 6:
                        return getString(R.string.card_workers);
                    case 7:
                        return getString(R.string.card_merchants);
                    case 8:
                        return getString(R.string.card_miners);
                    case 9:
                        return getString(R.string.card_fishermen);
                }
                break;
            }
            case 1: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_families);
                    case 1:
                        return getString(R.string.card_ambassadors);
                    case 2:
                        return getString(R.string.card_shepherds);
                }
                break;
            }
            case 2: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_place_of_refuge);
                    case 1:
                        return getString(R.string.card_home_country);
                    case 2:
                        return getString(R.string.card_road);
                    case 3:
                        return getString(R.string.card_advance);
                    case 4:
                        return getString(R.string.card_compass_points);
                    case 5:
                        return getString(R.string.card_fortress);
                }
                break;
            }
            case 3: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_geologists);
                    case 1:
                        return getString(R.string.card_messengers);
                    case 2:
                        return getString(R.string.card_noblewomen);
                    case 3:
                        return getString(R.string.card_vassals);
                    case 4:
                        return getString(R.string.card_captains);
                    case 5:
                        return getString(R.string.card_scouts);
                }
                break;
            }
            case 4: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_harvest_1);
                    case 1:
                        return getString(R.string.card_harvest_2);
                    case 2:
                        return getString(R.string.card_harvest_3);
                    case 3:
                        return getString(R.string.card_harvest_4);
                    case 4:
                        return getString(R.string.card_harvest_5);
                    case 5:
                        return getString(R.string.card_harvest_6);
                }
                break;
            }
            case 6: {
                switch (getCardName(card)) {
                    case 0:
                        return getString(R.string.card_emperor_s_choice_1);
                    case 1:
                        return getString(R.string.card_emperor_s_choice_2);
                    case 2:
                        return getString(R.string.card_emperor_s_choice_3);
                    case 3:
                        return getString(R.string.card_emperor_s_choice_4);
                    case 4:
                        return getString(R.string.card_emperor_s_choice_5);
                    case 5:
                        return getString(R.string.card_emperor_s_choice_6);
                    case 6:
                        return getString(R.string.card_emperor_s_choice_7);
                    case 7:
                        return getString(R.string.card_emperor_s_choice_8);
                }
                break;
            }
        }
        return "ERROR";
    }

    private String parseBoard(int set, int board) {
        switch (set) {
            case 0: {
                switch (board) {
                    case 0:
                        return getString(R.string.board_harbor);
                    case 1:
                        return getString(R.string.board_oracle);
                    case 2:
                        return getString(R.string.board_tavern);
                    case 3:
                        return getString(R.string.board_oasis);
                    case 4:
                        return getString(R.string.board_farmland);
                    case 5:
                        return getString(R.string.board_barn);
                    case 6:
                        return getString(R.string.board_tower);
                    case 7:
                        return getString(R.string.board_paddock);
                }
                break;
            }
            case 1: {
                switch (board) {
                    case 0:
                        return getString(R.string.board_quarry);
                    case 1:
                        return getString(R.string.board_caravan);
                    case 2:
                        return getString(R.string.board_gardens);
                    case 3:
                        return getString(R.string.board_village);
                }
                break;
            }
            case 2: {
                switch (board) {
                    case 0:
                        return getString(R.string.board_lighthouse_and_foresters_lodge);
                    case 1:
                        return getString(R.string.board_barracks_and_crossroads);
                    case 2:
                        return getString(R.string.board_city_hall_and_fort);
                    case 3:
                        return getString(R.string.board_wagon_and_monastery);
                }
                break;
            }
            case 3: {
                switch (board) {
                    case 0:
                        return getString(R.string.board_temple);
                    case 1:
                        return getString(R.string.board_refuge);
                    case 2:
                        return getString(R.string.board_canoe);
                    case 3:
                        return getString(R.string.board_fountain);
                }
                break;
            }
            case 4: {
                switch (board) {
                    case 0:
                        return getString(R.string.board_harvest_1);
                    case 1:
                        return getString(R.string.board_harvest_2);
                    case 2:
                        return getString(R.string.board_harvest_3);
                    case 3:
                        return getString(R.string.board_harvest_4);
                }
                break;
            }
            case 5: {
                switch (board) {
                    case 0:
                        return getString(R.string.island) + " - " + getString(R.string.board_island_bridge);
                    case 1:
                        return getString(R.string.island) + " - " + getString(R.string.board_island_treehouse);
                }
            }
        }
        return "ERROR";
    }
}