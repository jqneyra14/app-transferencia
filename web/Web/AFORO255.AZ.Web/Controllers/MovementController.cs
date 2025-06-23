using AFORO255.AZ.Web.DTOs.Movement;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using MS.AFORO255.Web.Service.History.Interfaces;
using AFORO255.AZ.Web.Extensions;

namespace AFORO255.AZ.Web.Controllers
{
    //[Authorize]
    public class MovementController : Controller
    {
        private readonly IMovementService _movementService;

        public MovementController(IMovementService movementService)
        {
            _movementService = movementService;
        }

        public async Task<IActionResult> Index(int accountId, DateTime? startDate, DateTime? endDate, string type, int page = 1, int size = 10)
        {
            var historyDTOResponses = await _movementService.GetByAccountId(User.Identity.GetToken(), accountId, startDate,endDate,type,page,size);
            return View(historyDTOResponses);
        }
    }
}
