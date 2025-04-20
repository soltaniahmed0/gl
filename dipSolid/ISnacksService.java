package com.example.Backend.Services;

import com.example.Backend.Entity.Snack;
import java.util.List;

public interface ISnacksService {
    List<Snack> getsnacks();

    void addsnack(Snack snack);

    Snack getsnack(int id);

    Snack update(Snack snack);

    void deleteSnack(int id);
}
